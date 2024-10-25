package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.chat.repository.IChatDAO;
import com.psugv.capstone.exception.InsertMessageException;
import com.psugv.capstone.exception.NoChatRoomException;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.util.ChatServer;
import com.psugv.capstone.util.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        LOGGER.debug("User: " + userModel.getName() + ", chat room: " + chatRoomId + ", send message: " + message);
        boolean insertion = chatDAO.insertMessage(message, userModel, chatRoomId);

        boolean sendToServer = ChatServer.sentMessage(message, userModel.getId(), Integer.parseInt(chatRoomId), userModel.getName());

        if((insertion && sendToServer) != true) {

            throw new InsertMessageException("send message service is not implemented corrected");
        }
        return true;
    }

    @Override
    public ChatRoomName selectChatRoom(String chatRoomID, UserModel userModel) {

        int chatRoomId = Integer.parseInt(chatRoomID);

        ChatRoom cr = chatDAO.findChatRoom(chatRoomId);

        ChatRoomName crn = chatDAO.findChatRoomName(userModel.getId(), chatRoomId);

        if(cr == null || crn == null){

            throw new NoChatRoomException("No such chat room or chat room name!!!");
        }

        LOGGER.info("Create new listener for new chat room");
        MessageListener ml = new MessageListener(cr, crn, userModel,messagingTemplate);

        LOGGER.info("Update new listener to server");
        ChatServer.updateOnlineUserPool(userModel.getId(), chatRoomId, ml);

        return crn;
    }

    @Override
    public List<Message> loadHistoryMessage(Integer chatRoomID) {

        return chatDAO.loadHistoryMessage(chatRoomID);
    }

    @Override
    public List<ChatRoomName> getAllChatRoomName(UserModel userModel){

        return chatDAO.getAllChatroomName(userModel.getId());
    }
}