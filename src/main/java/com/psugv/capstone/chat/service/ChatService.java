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
import org.springframework.scheduling.annotation.Scheduled;
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

    @Override
    public Boolean sendMessage(String message, UserModel userModel, String chatRoomId) {

        //Using chat DAO to save this message to the table.

        return ChatServer.sentMessage(message, userModel.getId(), Integer.parseInt(chatRoomId));
    }

    @Override
    public ChatRoomName selectChatRoom(String chatRoomID, UserModel userModel) {

        ChatRoom cr = chatDAO.findChatRoom(Integer.parseInt(chatRoomID));

        ChatRoomName crn = chatDAO.findChatRoomName(userModel.getId(), Integer.parseInt(chatRoomID));

        if(cr == null || crn == null){

            throw new NoChatRoomException("No such chat room or chat room name!!!");
        }

        MessageListener ml = new MessageListener(cr, crn, userModel);

        ChatServer.updateOnlineUserPool(userModel.getId(), Integer.parseInt(chatRoomID), ml);

        return crn;
    }

    @Override
    public List<Message> loadHistoryMessage(Integer chatRoomID, UserModel userModel) {

        return chatDAO.loadHistoryMessage(userModel.getId(), chatRoomID);
    }

    @Override
    public List<ChatRoomName> getAllChatRoomName(UserModel userModel){

        return chatDAO.getAllChatroomName(userModel.getId());
    }

    /*
    @Scheduled(fixedRate = 5000)
    public void sendUpdate() {

       messagingTemplate.convertAndSend("/topic/updates", "Update: " + System.currentTimeMillis());
    }*/
}
