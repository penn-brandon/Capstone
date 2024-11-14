package com.psugv.capstone.chat.repository;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.ChatRoomToUser;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.login.model.UserModel;

import java.util.List;

public interface IChatDAO {

    List<ChatRoomName> getAllChatroomName(Integer userId);

    List<Message> loadHistoryMessage(Integer chatroomId);

    ChatRoom findChatRoom(Integer chatRoomId);

    ChatRoomName findChatRoomName(Integer userId, Integer chatRoomId);

    boolean insertMessage(String message, UserModel userModel, String chatRoomId);

    List<UserModel> blurrySearchUsername(String input);

    void updateChatRoomName(ChatRoomName chatRoomName, UserModel userModel);

    ChatRoom createNewChatRoom();

    void insertNewChatRoomName(ChatRoom chatRoom, Integer userId, String name);

    void insertChatRoomToUser(ChatRoomToUser chatRoomToUser);

    List<ChatRoomToUser> findChatRoomToUserByChatRoom(Integer chatRoomId);

    List<ChatRoomToUser> findChatRoomToUserByUserID(Integer userId);
}
