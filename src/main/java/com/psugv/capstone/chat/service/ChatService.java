package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.chat.repository.IChatDAO;
import com.psugv.capstone.exception.NoChatRoomException;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.util.ChatServer;
import com.psugv.capstone.util.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class ChatService implements IChatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    IChatDAO chatDAO;

    @Autowired
    SimpMessagingTemplate messagingTemplate;

    @Override
    public Boolean sendMessage(String message, UserModel userModel, String chatRoomId) {

        //Using chat DAO to save this message to the table.

        return ChatServer.sentMessage(message, userModel.getId(), Integer.parseInt(chatRoomId));
    }

    @Override
    public ChatRoomName selectChatRoom(String chatRoomID, UserModel userModel) {

        int chatRoomId = Integer.parseInt(chatRoomID);

        ChatRoom cr = chatDAO.findChatRoom(chatRoomId);

        ChatRoomName crn = chatDAO.findChatRoomName(userModel.getId(), chatRoomId);

        if(cr == null || crn == null){

            throw new NoChatRoomException("No such chat room or chat room name!!!");
        }

        // MessageListener ml = new MessageListener(cr, crn, userModel);

        //ChatServer.updateOnlineUserPool(userModel.getId(), chatRoomId, ml);

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

    @Override
    public void sendUpdate(MessageListener messageListener, String message) {

       String userName = messageListener.getUser().getUsername();

       LOGGER.info("Publish the data to " + "/listening/" + userName);

       messagingTemplate.convertAndSend("/listening/" + userName, message);
    }
}