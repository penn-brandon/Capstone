package com.psugv.capstone.chat.repository;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@Repository
public class ChatDAO implements IChatDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatDAO.class);

    @Autowired
    EntityManager entityManager;

    private static final String CHAT_ROOM_NAME_POSTFIX = "_chatroomname";

    private static final String MESSAGE_POSTFIX = "_message";

    @Override
    public List<ChatRoomName> getAllChatroomName(Integer userId){

        List<ChatRoomName> result;

        StringBuilder sql = new StringBuilder().append("select * from ").append(userId).append(CHAT_ROOM_NAME_POSTFIX).append(" order by last_modified desc");

        try {
            Query query = entityManager.createNativeQuery(sql.toString());

            result = (List<ChatRoomName>) query.getResultList();

        }catch(Exception e){

            LOGGER.error("Fail to load chat room name list!!", e);

            return null;
        }
        return result;
    }

    @Override
    public List<Message> loadHistoryMessage(Integer chatroomId){

        List<Message> result;

        StringBuilder sql = new StringBuilder().append("select * from ").append(chatroomId).append(MESSAGE_POSTFIX).append(" order by time desc");

        LOGGER.debug(sql.toString());

        try {
            Query query = entityManager.createNativeQuery(sql.toString(), Message.class);

            result = (List<Message>) query.getResultList();

        }catch(Exception e){

            LOGGER.error("Fail to load message list!!", e);

            return null;
        }
        return result;
    }

    @Override
    public ChatRoomName findChatRoomName(Integer userId, Integer chatRoomId) {

        ChatRoomName result;

        try{
            StringBuilder sql = new StringBuilder().append("select * from ").append(userId).append(CHAT_ROOM_NAME_POSTFIX).append(" where chat_room_id = ").append(chatRoomId);

            Query query = entityManager.createNativeQuery(sql.toString(), ChatRoomName.class);

            result = (ChatRoomName) query.getSingleResult();

        } catch (Exception e){

            LOGGER.error("Fail to find the chat room name", e);

            return null;
        }
        return result;
    }

    @Override
    public ChatRoom findChatRoom(Integer chatroomId){

        ChatRoom result;

        try {
            Query query = entityManager.createQuery("from chatroom where id = :chatroomId", ChatRoom.class);

            query.setParameter("chatroomId", chatroomId);

            result = (ChatRoom) query.getSingleResult();

        }catch(Exception e){

            LOGGER.error("Fail to find chat room!!", e);

            return null;
        }
        return result;
    }

    @Override
    public boolean insertMessage(String message, UserModel userModel, String chatRoomId){

        Date currentDate = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String formattedDate = sdf.format(currentDate);

        try {
            Query query = entityManager.createNativeQuery("insert into " + chatRoomId + MESSAGE_POSTFIX + "(content,time,senderId,sender) " +
                    "value (\"" + message + "\",\"" + formattedDate + "\"," + userModel.getId() + ",\"" + userModel.getName() + "\");");

            int result =  query.executeUpdate();
            LOGGER.debug(result + " rows inserted");

            return true;

        }catch(Exception e){

            LOGGER.error("Cannot insert message" + message, e);

            throw new InsertErrorException("Cannot insert message" + message);
        }
    }

    public List<UserModel> blurrySearchUsername(String input){

        List<UserModel> result;
        try{
            result = entityManager.createQuery("from user where username like '%chu%'");

        } catch(Exception e){

            LOGGER.error("See what the message would be", e.getMessage());
        }
        return result;
    }
}
