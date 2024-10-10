package com.psugv.capstone.chat.repository;

import com.psugv.capstone.chat.model.ChatRoomName;

public interface IChatDAO {

    public ChatRoomName getAllChatroomName(Integer chatroomId, Integer userId);
}
