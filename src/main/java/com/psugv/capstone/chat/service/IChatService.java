package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.login.model.UserModel;

import java.util.List;

public interface IChatService {

    public Boolean sendMessage(String message, UserModel userModel, String chatRoomId);

    public ChatRoomName selectChatRoom(String chatRoomID, UserModel userModel);

    public List<ChatRoomName> getAllChatRoomName(UserModel userModel);

    public List<Message> loadHistoryMessage(Integer chatRoomID, UserModel userModel);

    //public void sendUpdate();
}
