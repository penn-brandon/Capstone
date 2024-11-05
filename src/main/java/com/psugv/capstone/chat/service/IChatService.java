package com.psugv.capstone.chat.service;

import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.login.model.UserModel;

import java.util.List;
import java.util.Map;

public interface IChatService {

    public Boolean sendMessage(String message, UserModel userModel, String chatRoomId);

    public ChatRoomName selectChatRoom(String chatRoomID, UserModel userModel);

    public List<ChatRoomName> getAllChatRoomName(UserModel userModel);

    public List<Message> loadHistoryMessage(Integer chatRoomID);

    public List<UserModel> searchUser(String input);

    public ChatRoomName createChatRoom(Map<String, String> inputMap, UserModel userModel);
}
