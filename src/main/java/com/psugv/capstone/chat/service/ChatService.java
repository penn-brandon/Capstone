package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.chat.repository.IChatDAO;
import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.exception.NoQueryResultException;
import com.psugv.capstone.login.model.UserModel;
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

        List<ChatRoomName> aiteChatRoomList = chatDAO.getAllChatroomName(aitaID);

        List<ChatRoomName> jibunnChatRoomList = chatDAO.getAllChatroomName(jibunnId);

        List<Integer> chatRoomComparison;

        if(aiteChatRoomList.isEmpty() || jibunnChatRoomList.isEmpty()){

            LOGGER.debug("One user has no ChaRroomName");
            chatRoomComparison = new LinkedList<>();

        } else {

            List<Integer> aiteChatRoomID = new LinkedList<>();

            for(ChatRoomName chatRoom : aiteChatRoomList){

                aiteChatRoomID.add(chatRoom.getChatRoom().getId());
            }

            List<Integer> jibunnChatRoomID = new LinkedList<>();

            for(ChatRoomName chatRoom : jibunnChatRoomList){

                jibunnChatRoomID.add(chatRoom.getChatRoom().getId());
            }
            chatRoomComparison = Utility.commonIdComparator(aiteChatRoomID, jibunnChatRoomID);
        }
        ChatRoomName crn = null;

        if(!chatRoomComparison.isEmpty()){

            for (Integer integer : chatRoomComparison) {

                ChatRoom cr = chatDAO.findChatRoom(integer);

                if (!cr.getJoinable()) {

                    crn = selectChatRoom(cr.getId().toString(), userModel);
                }
            }
        }
        if(crn == null){

            ChatRoom cr = chatDAO.createNewChatRoom();

            chatDAO.insertNewChatRoomName(cr, aitaID, userModel.getName());

            chatDAO.insertNewChatRoomName(cr, jibunnId, inputMap.get("name"));

            crn = selectChatRoom(cr.getId().toString(), userModel);
        }
        return crn;
    }

    @Override
    public void deselectChatRoom (UserModel userModel){

        ChatServer.removeFromOnlineUserPool(userModel.getId());
    }
}