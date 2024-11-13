package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.ChatRoomToUser;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.chat.repository.IChatDAO;
import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.exception.NoQueryResultException;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.repository.IUserDAO;
import com.psugv.capstone.util.ChatServer;
import com.psugv.capstone.util.MessageListener;
import com.psugv.capstone.util.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@EnableTransactionManagement
public class ChatService implements IChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    IChatDAO chatDAO;

    @Autowired
    IUserDAO userDAO;

    @Autowired
    private MessageListener messageListener;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public Boolean sendMessage(String message, UserModel userModel, String chatRoomId) {

        LOGGER.debug("User: {}, chat room: {}, send message: {}", userModel.getName(), chatRoomId, message);
        boolean insertion = chatDAO.insertMessage(message, userModel, chatRoomId);

        boolean sendToServer = ChatServer.sentMessage(message, userModel.getId(), Integer.parseInt(chatRoomId), userModel.getName());

        if (!(insertion && sendToServer)) {

            throw new InsertErrorException("send message service is not implemented corrected");
        }
        return true;
    }

    @Override
    public ChatRoomName selectChatRoom(String chatRoomID, UserModel userModel) {

        int chatRoomId = Integer.parseInt(chatRoomID);

        ChatRoom cr = chatDAO.findChatRoom(chatRoomId);

        ChatRoomName crn = chatDAO.findChatRoomName(userModel.getId(), chatRoomId);

        crn.setLastModified(new Date());

        chatDAO.updateChatRoomName(crn);

        if (cr == null || crn == null) {

            throw new NoQueryResultException("No such chat room or chat room name!!!");
        }

        LOGGER.info("Create new listener for new chat room");
        MessageListener ml = new MessageListener(cr, crn, userModel, messagingTemplate);

        LOGGER.info("Update new listener to server");
        ChatServer.updateOnlineUserPool(userModel.getId(), chatRoomId, ml);

        return crn;
    }

    @Override
    public List<Message> loadHistoryMessage (Integer chatRoomID) {

        return chatDAO.loadHistoryMessage(chatRoomID);
    }

    @Override
    public List<ChatRoomName> getAllChatRoomName (UserModel userModel){


        return chatDAO.getAllChatroomName(userModel.getId());
    }

    @Override
    public List<UserModel> searchUser (String input){

        List<UserModel> result;

        try {
            result = chatDAO.blurrySearchUsername(input);

        } catch (NoQueryResultException e){

            LOGGER.warn("Username does not exist, please try again");
            return null;
        }
        return result;
    }

    @Override
    public synchronized ChatRoomName createChatRoom (Map<String, String> inputMap, UserModel userModel){

        Integer aitaID = Integer.parseInt(inputMap.get("id"));

        Integer jibunnId = userModel.getId();

        List<ChatRoomToUser> aitaIDChatRoomToUser = chatDAO.findChatRoomToUserByUserID(aitaID);

        List<ChatRoomToUser> jibunnIdChatRoomToUser = chatDAO.findChatRoomToUserByUserID(jibunnId);

        List<Integer> aitaChatRoomIDList = new LinkedList<>();

        for(ChatRoomToUser aita : aitaIDChatRoomToUser){

            aitaChatRoomIDList.add(aita.getChatRoom().getId());
        }

        List<Integer> jibunnChatRoomIDList = new LinkedList<>();

        for(ChatRoomToUser jibunn : jibunnIdChatRoomToUser){

            jibunnChatRoomIDList.add(jibunn.getChatRoom().getId());
        }

        List<Integer> cummonChatRoomID = Utility.commonIdComparator(aitaChatRoomIDList, jibunnChatRoomIDList);

        ChatRoomName crn = null;

        if(!cummonChatRoomID.isEmpty()){

            for (Integer integer : cummonChatRoomID) {

                List<ChatRoomToUser>  crtuList = chatDAO.findChatRoomToUserByChatRoom(integer);

                if (crtuList.size() == 2) {

                    crn = selectChatRoom(integer.toString(), userModel);
                }
            }
        }
        if(crn == null){

            ChatRoom cr = chatDAO.createNewChatRoom();

            chatDAO.insertNewChatRoomName(cr, aitaID, userModel.getName());

            UserModel aite = userDAO.findUserById(aitaID);

            ChatRoomToUser aiteRow = new ChatRoomToUser(null, aite, cr);

            chatDAO.insertChatRoomToUser(aiteRow);

            chatDAO.insertNewChatRoomName(cr, jibunnId, inputMap.get("name"));

            ChatRoomToUser jibunnRow = new ChatRoomToUser(null, userModel, cr);

            chatDAO.insertChatRoomToUser(jibunnRow);

            crn = selectChatRoom(cr.getId().toString(), userModel);
        }

        return crn;
    }

    @Override
    public void deselectChatRoom (UserModel userModel){

        ChatServer.removeFromOnlineUserPool(userModel.getId());
    }

    @Override
    public ChatRoomName addUserToChatRoom(Map<String, String> inputMap, UserModel userModel){

        Integer chatRoomId = Integer.parseInt(inputMap.get("chatroom"));

        UserModel aite = userDAO.findUserById(Integer.parseInt(inputMap.get("id")));

        ChatRoom chatRoom = chatDAO.findChatRoom(chatRoomId);

        ChatRoomToUser crtu = new ChatRoomToUser(null, aite, chatRoom);

        chatDAO.insertChatRoomToUser(crtu);

        List<ChatRoomToUser> chatRoomIdChatRoomToUser = chatDAO.findChatRoomToUserByChatRoom(chatRoom.getId());

        List<UserModel> userList = new LinkedList<>();

        for (ChatRoomToUser temp: chatRoomIdChatRoomToUser){

            userList.add(userDAO.findUserById(temp.getUserModel().getId()));
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 3; i++){

            sb.append(userList.get(i).getName());
            sb.append(", ");
        }

        sb.append("and " );

        sb.append(userList.size() - 3);

        sb.append(" people");

        String newName = sb.toString();

        chatDAO.insertNewChatRoomName(chatRoom, aite.getId(), newName);

        for(UserModel um: userList){

            if(um.getId().equals(aite.getId())){

                continue;
            }

            ChatRoomName tempCRN = chatDAO.findChatRoomName(um.getId(), chatRoomId);

            tempCRN.setChatRoomName(newName);

            chatDAO.updateChatRoomName(tempCRN);
        }
        return chatDAO.findChatRoomName(userModel.getId(), chatRoomId);
    }
}