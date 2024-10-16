package com.psugv.capstone.chat.repository;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;

import java.util.List;

public interface IChatDAO {

    public List<ChatRoomName> getAllChatroomName(Integer userId);

    public List<Message> loadHistoryMessage(Integer userId, Integer chatroomId);

    public ChatRoom findChatRoom(Integer chatRoomId);

    public ChatRoomName findChatRoomName(Integer userId, Integer chatRoomId);
}
