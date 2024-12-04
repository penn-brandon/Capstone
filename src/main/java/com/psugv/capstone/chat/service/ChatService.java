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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This is the service class containing the major business logic.
 * It should be invoked by controller.
 * Author: Chuan Wei
 */
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
    private SimpMessagingTemplate messagingTemplate;

    @Override
    public Boolean sendMessage(String message, UserModel userModel, String chatRoomId) {

        LOGGER.trace("In ChatService sendMessage method");
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

        LOGGER.trace("In ChatService selectChatRoom method");
        int chatRoomId = Integer.parseInt(chatRoomID);
        LOGGER.debug("Find chat room id: {}", chatRoomId);

        ChatRoom cr = chatDAO.findChatRoom(chatRoomId);

        ChatRoomName crn = chatDAO.findChatRoomName(userModel.getId(), chatRoomId);

        if (cr == null || crn == null) {

            throw new NoQueryResultException("No such chat room or chat room name!!!");
        }
        LOGGER.trace("ChatRoom and ChatRoomName are not null^_^");
        chatDAO.updateChatRoomName(userModel, crn.getAdmin(), crn.getChatRoom().getId(), crn.getChatRoomName(), crn.getId());

        LOGGER.debug("Create new listener for new chat room");
        MessageListener ml = new MessageListener(cr, crn, userModel, messagingTemplate);

        LOGGER.debug("Update new listener to server");
        ChatServer.updateOnlineUserPool(userModel.getId(), chatRoomId, ml);

        return crn;
    }

    @Override
    public List<Message> loadHistoryMessage(UserModel usermodel, Integer chatRoomID) {

        LOGGER.debug("load history message service");

        //Check if user in the chat room.
        ChatRoomToUser chatRoomToUser = chatDAO.findChatRoomToUserByUserId(chatRoomID, usermodel.getId());

        if (chatRoomToUser == null) {

            LOGGER.error("User " + usermodel.getId() + " is trying to access chat room " + chatRoomID);
            return null;
        }

        return chatDAO.loadHistoryMessage(chatRoomID);
    }

    @Override
    public List<ChatRoomName> getAllChatRoomName(UserModel userModel) {

        LOGGER.trace("In ChatService getAllChatRoomName method");
        ChatServer.loginCheckin(userModel.getId());

        return chatDAO.getAllChatroomName(userModel.getId());
    }

    @Override
    public List<UserModel> searchUser(String input) {

        LOGGER.trace("In ChatService searchUser method");
        List<UserModel> result;

        try {
            result = chatDAO.blurrySearchUsername(input);

        } catch (NoQueryResultException e) {

            LOGGER.warn("Username does not exist, please try again");
            return null;
        }
        return result;
    }

    @Override
    public synchronized ChatRoomName createChatRoom(Map<String, String> inputMap, UserModel userModel) {

        LOGGER.trace("In ChatService createChatRoom method");
        /*
        Getting both user id to compare if there is existing chat room.
         */
        Integer aitaID = Integer.parseInt(inputMap.get("id"));
        LOGGER.trace("Id of the user used to create new chat:{}", aitaID);

        Integer jibunnId = userModel.getId();
        LOGGER.trace("My ID:{}", jibunnId);

        LOGGER.trace("Finding all chat room for both users");
        List<ChatRoomToUser> aitaIDChatRoomToUser = new ArrayList<>(chatDAO.findChatRoomToUserByUserID(aitaID));

        List<ChatRoomToUser> jibunnIdChatRoomToUser = new ArrayList<>(chatDAO.findChatRoomToUserByUserID(jibunnId));

        List<Integer> commonChatRoomID;

        LOGGER.trace("Checking non of them are empty.");
        if (aitaIDChatRoomToUser == null || jibunnIdChatRoomToUser == null || aitaIDChatRoomToUser.isEmpty() || jibunnIdChatRoomToUser.isEmpty()) {

            LOGGER.debug("no common chat room ID");
            commonChatRoomID = new LinkedList<>();

        } else {

            LOGGER.debug("Getting and comparing chat room ID");
            List<Integer> aitaChatRoomIDList = new LinkedList<>();

            LOGGER.debug("create ID list for added user");
            for (ChatRoomToUser aita : aitaIDChatRoomToUser) {

                Integer tempID = aita.getChatRoom().getId();

                LOGGER.trace("{}\n", tempID);
                aitaChatRoomIDList.add(tempID);
            }

            List<Integer> jibunnChatRoomIDList = new LinkedList<>();

            LOGGER.debug("create ID list for user");
            for (ChatRoomToUser jibunn : jibunnIdChatRoomToUser) {

                Integer tempID = jibunn.getChatRoom().getId();

                LOGGER.trace("{}\n", tempID);
                jibunnChatRoomIDList.add(tempID);
            }
            commonChatRoomID = Utility.commonIdComparator(aitaChatRoomIDList, jibunnChatRoomIDList);
            LOGGER.trace("Common ID list got: {}", commonChatRoomID);
        }
        ChatRoomName crn = null;

        /*
        there is common chat room, check is it's specific for two people.
         */
        LOGGER.debug("Check common chat room");
        if (!commonChatRoomID.isEmpty()) {

            for (Integer integer : commonChatRoomID) {

                List<ChatRoomToUser> crtuList = chatDAO.findChatRoomToUserByChatRoom(integer);

                if (crtuList.size() == 2) {

                    LOGGER.trace("If the size is 2 means they have already have chat room");
                    LOGGER.debug("Exact chat room found!!");
                    crn = selectChatRoom(integer.toString(), userModel);
                }
            }
        }
        LOGGER.trace("If chat room not found means there are no existing chat room.");
        if (crn == null) {

            LOGGER.debug("create new chat room and new chat room name for both.");
            ChatRoom cr = chatDAO.createNewChatRoom();

            LOGGER.trace("Create new message table for chat room");
            chatDAO.createNemMessage(cr.getId());

            LOGGER.trace("Insert new ChatRoomName for added user by giving user's username");
            chatDAO.insertNewChatRoomName(cr, aitaID, userModel.getName());

            UserModel aite = userDAO.findUserById(aitaID);

            ChatRoomToUser aiteRow = new ChatRoomToUser(null, aite, cr);

            chatDAO.insertChatRoomToUser(aiteRow);

            LOGGER.trace("Insert new ChatRoomName for user by giving added user's username");
            chatDAO.insertNewChatRoomName(cr, jibunnId, inputMap.get("name"));

            ChatRoomToUser jibunnRow = new ChatRoomToUser(null, userModel, cr);

            chatDAO.insertChatRoomToUser(jibunnRow);

            LOGGER.trace("Returning chat room for user");
            crn = selectChatRoom(cr.getId().toString(), userModel);
        }

        return crn;
    }

    @Override
    public void deselectChatRoom(UserModel userModel) {

        LOGGER.trace("In ChatService deselectChatRoom method");
        ChatServer.removeFromOnlineUserPool(userModel.getId());
    }

    @Override
    public ChatRoomName addUserToChatRoom(Map<String, String> inputMap, UserModel userModel) {

        LOGGER.trace("In ChatService addUserToChatRoom method");
        Integer chatRoomId;
        LOGGER.debug(inputMap.toString());

        try {
            LOGGER.trace("Get ChatRoom ID");
            chatRoomId = Integer.parseInt(inputMap.get("chatroom"));

        } catch (NumberFormatException e) {

            LOGGER.error("Chat room id is:" + inputMap.get("chatroom"));
            throw new NoQueryResultException("Cannot find chat room ID");
        }

        LOGGER.trace("get added user's ID");
        UserModel aite = userDAO.findUserById(Integer.parseInt(inputMap.get("id")));

        /*
        Check if user is in the chat room already.
         */
        ChatRoomName crn = null;

        LOGGER.trace("Get existing chat room name of added user.");
        crn = chatDAO.findChatRoomName(aite.getId(), chatRoomId);

        if (crn != null) {

            LOGGER.trace("Added user already in the chat room");
            return crn;
        }

        ChatRoom chatRoom = chatDAO.findChatRoom(chatRoomId);
        LOGGER.debug("search chat room id: " + chatRoomId);

        LOGGER.trace("Adding added user to chat room");
        ChatRoomToUser crtu = new ChatRoomToUser(null, aite, chatRoom);

        chatDAO.insertChatRoomToUser(crtu);

        LOGGER.trace("Finding all users in the chat room");
        /*
        To change the chat room name for all user.
         */
        List<ChatRoomToUser> chatRoomIdChatRoomToUser = chatDAO.findChatRoomToUserByChatRoom(chatRoom.getId());

        List<UserModel> userList = new LinkedList<>();

        for (ChatRoomToUser temp : chatRoomIdChatRoomToUser) {

            userList.add(userDAO.findUserById(temp.getUserModel().getId()));
        }
        StringBuilder sb = new StringBuilder();

        LOGGER.trace("Concactinating names of user");
        for (int i = 0; i < 3; i++) {

            sb.append(userList.get(i).getName());
            sb.append(", ");
        }

        sb.append("and ");

        sb.append(userList.size() - 3);

        sb.append(" people");

        String newName = sb.toString();

        LOGGER.trace("Changing the name by delete and insert new row.");
        LOGGER.debug("insert for new comer");
        chatDAO.insertNewChatRoomName(chatRoom, aite.getId(), newName);

        LOGGER.debug("delete and insert new row for existing user.");
        for (UserModel um : userList) {

            if (um.getId().equals(aite.getId())) {
                LOGGER.trace("Skipping new user");
                continue;
            }
            LOGGER.debug("current user is: " + um.getId());
            LOGGER.debug("current chat room is: " + chatRoomId);
            ChatRoomName tempCRN = chatDAO.findChatRoomName(um.getId(), chatRoomId);

            LOGGER.trace("tempCRN is null? " + (tempCRN == null));
            if (tempCRN == null) {

                throw new RuntimeException("tempCRN is null");
            }
            LOGGER.trace("Updating existing chat room name");
            chatDAO.deleteChatRoomName(tempCRN.getId(), um.getId());

            chatDAO.insertNewChatRoomName(tempCRN.getChatRoom(), um.getId(), newName);
        }
        LOGGER.trace("Returning chat room name");
        return chatDAO.findChatRoomName(userModel.getId(), chatRoomId);
    }
}