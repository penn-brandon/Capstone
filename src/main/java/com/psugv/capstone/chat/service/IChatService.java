package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.login.model.UserModel;

import java.util.List;

public interface IChatService {

    public Boolean sendMessage(String message, String chatRoomId, UserModel userModel);

    public ChatRoom selectChatRoom(String chatRoomID, UserModel userModel);

    public List<ChatRoomName> getAllChatRoomName(UserModel userModel);
}
