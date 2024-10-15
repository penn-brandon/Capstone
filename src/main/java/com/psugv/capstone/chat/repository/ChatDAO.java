package com.psugv.capstone.chat.repository;

import com.psugv.capstone.chat.model.ChatRoomName;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatDAO {

    @Autowired
    EntityManager entityManager;

    private static final String CHAT_ROOM_NAME_POSTFIX = "_chat_room_name";

    private static final String MESSAGE_POSTFIX = "_message";

    public List<ChatRoomName> getAllChatroomName(Integer userId){

         List<ChatRoomName> result;

        try {
            Query query = entityManager.createNativeQuery("select * from " + userId.toString() + CHAT_ROOM_NAME_POSTFIX + "order by last_modified desc");

            result = (List<ChatRoomName>) query.getResultList();

        }catch(Exception e){

            e.printStackTrace();

            return null;
        }
        return result;
    }
}
