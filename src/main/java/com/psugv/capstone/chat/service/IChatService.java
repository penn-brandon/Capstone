package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.login.model.UserModel;

import java.util.List;

public interface IChatService {

    Boolean sendMessage(String message, UserModel userModel, String chatRoomId);

    ChatRoomName selectChatRoom(String chatRoomID, UserModel userModel);

    List<ChatRoomName> getAllChatRoomName(UserModel userModel);

    List<Message> loadHistoryMessage(Integer chatRoomID);
}
