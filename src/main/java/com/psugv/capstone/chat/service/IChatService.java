package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.login.model.UserModel;

import java.util.List;
import java.util.Map;

public interface IChatService {

    Boolean sendMessage(String message, UserModel userModel, String chatRoomId);

    ChatRoomName selectChatRoom(String chatRoomID, UserModel userModel);

    List<ChatRoomName> getAllChatRoomName(UserModel userModel);

    List<Message> loadHistoryMessage(Integer chatRoomID);

    List<UserModel> searchUser(String input);

    ChatRoomName createChatRoom(Map<String, String> inputMap, UserModel userModel);

    void deselectChatRoom(UserModel userModel);
}
