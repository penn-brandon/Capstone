package com.psugv.capstone.chat.repository;

import com.psugv.capstone.chat.model.ChatRoomName;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;

public class ChatDAO {

    @Autowired
    EntityManager entityManager;

    private static final String chatRoomNamePrefix = "_chat_room_name_id";

    public ChatRoomName getAllChatroomName(Integer chatroomId, Integer userId){

        Query query = entityManager.createNativeQuery("select * from " + userId.toString() + chatRoomNamePrefix);

        query.setParameter(1, userName);
    }
}
