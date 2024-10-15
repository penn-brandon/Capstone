package com.psugv.capstone.chat.repository;

import com.psugv.capstone.chat.model.ChatRoomName;

import java.util.List;

public interface IChatDAO {

    public List<ChatRoomName> getAllChatroomName(Integer chatroomId, Integer userId);
}
