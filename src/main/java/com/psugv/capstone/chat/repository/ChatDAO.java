package com.psugv.capstone.chat.repository;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.ChatRoomToUser;
import com.psugv.capstone.chat.model.Message;
import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Repository
public class ChatDAO implements IChatDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatDAO.class);
    private static final String CHAT_ROOM_NAME_POSTFIX = "_chatroomname";
    private static final String MESSAGE_POSTFIX = "_message";
    @Autowired
    EntityManager entityManager;

    @Override
    public List<ChatRoomName> getAllChatroomName(Integer userId) {

        List<ChatRoomName> result;

        StringBuilder sql = new StringBuilder().append("select * from ").append(userId).append(CHAT_ROOM_NAME_POSTFIX).append(" order by last_modified desc");

        try {
            Query query = entityManager.createNativeQuery(sql.toString());

            result = (List<ChatRoomName>) query.getResultList();

        } catch (Exception e) {

            LOGGER.error("Fail to load chat room name list!!", e);

            return null;
        }
        return result;
    }

    @Override
    public List<Message> loadHistoryMessage(Integer chatroomId) {

        List<Message> result;

        StringBuilder sql = new StringBuilder().append("select * from ").append(chatroomId).append(MESSAGE_POSTFIX).append(" order by time desc");

        LOGGER.debug(sql.toString());

        try {
            Query query = entityManager.createNativeQuery(sql.toString(), Message.class);

            result = (List<Message>) query.getResultList();

        } catch (Exception e) {

            LOGGER.error("Fail to load message list!!", e);

            return null;
        }
        return result;
    }

    @Override
    public ChatRoomName findChatRoomName(Integer userId, Integer chatRoomId) {

        ChatRoomName result;

        try {

            Query query = entityManager.createNativeQuery("select * from " + userId + CHAT_ROOM_NAME_POSTFIX + " where chat_room_id = " + chatRoomId, ChatRoomName.class);

            result = (ChatRoomName) query.getSingleResult();

        } catch (Exception e) {

            LOGGER.error("Fail to find the chat room name", e);

            return null;
        }
        return result;
    }

    @Override
    public ChatRoom findChatRoom(Integer chatroomId) {

        ChatRoom result;

        try {
            Query query = entityManager.createQuery("from chatroom where id = :chatroomId", ChatRoom.class);

            query.setParameter("chatroomId", chatroomId);

            result = (ChatRoom) query.getSingleResult();

        } catch (Exception e) {

            LOGGER.error("Fail to find chat room!!", e);

            return null;
        }
        return result;
    }

    @Override
    public boolean insertMessage(String message, UserModel userModel, String chatRoomId) {

        Date currentDate = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String formattedDate = sdf.format(currentDate);

        try {
            Query query = entityManager.createNativeQuery("insert into " + chatRoomId + MESSAGE_POSTFIX + "(content,time,senderId,sender) " +
                    "value (\"" + message + "\",\"" + formattedDate + "\"," + userModel.getId() + ",\"" + userModel.getName() + "\");");

            int result = query.executeUpdate();
            LOGGER.debug("{} rows inserted", result);

            return true;

        } catch (Exception e) {

            LOGGER.error("Cannot insert message {}", message, e);

            throw new InsertErrorException("Cannot insert message" + message);
        }
    }

    @Override
    public List<UserModel> blurrySearchUsername(String input){

        List<UserModel> result;
        try{
            String sql = "from user where username like '%" + input + "%'";
            result = entityManager.createQuery(sql, UserModel.class).getResultList();

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return result;
    }

    @Override
    public void updateChatRoomName(ChatRoomName chatRoomName, UserModel userModel){

        LOGGER.debug("Update chat room name DAO");
        String sql = "update " + userModel.getId() + CHAT_ROOM_NAME_POSTFIX +
                " set admin=" + chatRoomName.getAdmin() +
                ",chat_room_id=" + chatRoomName.getChatRoom().getId() +
                ",chat_room_name=\"" + chatRoomName.getChatRoomName() + "\"" +
                ",last_modified=CURRENT_TIMESTAMP " +
                "where chat_room_name_id=" + chatRoomName.getId() +";";
        try{
            LOGGER.debug("SQL string: " + sql);
            Query query = entityManager.createNativeQuery(sql);

            query.executeUpdate();

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public ChatRoom createNewChatRoom(){

        ChatRoom chatRoom = new ChatRoom(null, false);

        try{
            entityManager.persist(chatRoom);
            entityManager.flush();

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
        }
        return chatRoom;
    }

    @Override
    public void insertNewChatRoomName(ChatRoom chatRoom, Integer userId, String name){

        String sql = "insert into " + userId + CHAT_ROOM_NAME_POSTFIX + " (chat_room_id, admin, chat_room_name, last_modified) value (" + chatRoom.getId() + ",False," + name + ", CURRENT_TIMESTAMP);";

        try{
            entityManager.createNativeQuery(sql).executeUpdate();

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public void insertChatRoomToUser(ChatRoomToUser chatRoomToUser){

        try{
            entityManager.persist(chatRoomToUser);

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<ChatRoomToUser> findChatRoomToUserByChatRoom(Integer chatRoomId){

        List<ChatRoomToUser> result;

        try{
            result = (List<ChatRoomToUser>) entityManager.createQuery("from ChatRoomToUser where chat_room_id = :chatRoomId").setParameter("chatRoomId", chatRoomId).getResultList();

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return result;
    }

    @Override
    public List<ChatRoomToUser> findChatRoomToUserByUserID(Integer userId){

        List<ChatRoomToUser> result;

        try{
            result = (List<ChatRoomToUser>) entityManager.createQuery("from ChatRoomToUser where user_id = :userId").setParameter("userId", userId).getResultList();

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return result;
    }
}