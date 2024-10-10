package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.util.ChatServer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@EnableTransactionManagement
public class ChatService implements IChatService {

    @Override
    public Boolean sendMessage(String message, String chatRoomId, UserModel userModel) {

        //Using chat DAO to save this message to the table.

        return ChatServer.sentMessage(message, userModel.getId());
    }

    @Override
    public ChatRoom selectChatRoom(String chatRoomID, UserModel userModel){

        //Using chat DAO to get the exact chatroom this message to the table.
    }

    @Override
    public List<ChatRoomName> getAllChatRoomName(UserModel userModel){

        //Using chat DAO to get the exact chatroom this message to the table.
    }
}
