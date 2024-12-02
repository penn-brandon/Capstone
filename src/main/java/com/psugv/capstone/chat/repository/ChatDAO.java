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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This is data access object class.
 * It's used to provide interaction to DB that service class need.
 *
 * Author: Chuan Wei
 */
@Repository
public class ChatDAO implements IChatDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatDAO.class);
    private static final String CHAT_ROOM_NAME_POSTFIX = "_ChatRoomName";
    private static final String MESSAGE_POSTFIX = "_message";

    @Autowired
    EntityManager entityManager;

    @Value("${spring.datasource.url}")
    private String URL;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public List<ChatRoomName> getAllChatroomName(Integer userId) {

        LOGGER.trace("In ChatDAO getAllChatroomName method");
        List<ChatRoomName> result;

        StringBuilder sql = new StringBuilder().append("select * from ").append(userId).append(CHAT_ROOM_NAME_POSTFIX).append(" order by last_modified desc");
        LOGGER.trace("sql: {}", sql);

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

        LOGGER.trace("In ChatDAO loadHistoryMessage method");
        List<Message> result;

        StringBuilder sql = new StringBuilder().append("select * from ").append(chatroomId).append(MESSAGE_POSTFIX).append(" order by time desc");
        LOGGER.trace("sql: {}", sql);

        try {
            Query query = entityManager.createNativeQuery(sql.toString(), Message.class);

            result = (List<Message>) query.getResultList();
            LOGGER.trace("history message is empty? " + result.isEmpty());

        } catch (Exception e) {

            LOGGER.error("Fail to load message list!!", e);
            return null;
        }
        return result;
    }

    @Override
    public ChatRoomName findChatRoomName(Integer userId, Integer chatRoomId) {

        LOGGER.trace("In ChatDAO findChatRoomName method");
        ChatRoomName result;

        try {

            Query query = entityManager.createNativeQuery("select * from " + userId + CHAT_ROOM_NAME_POSTFIX + " where chat_room_id = ?", ChatRoomName.class);
            query.setParameter(1, chatRoomId);

            LOGGER.trace("Execute get query");
            result = (ChatRoomName) query.getSingleResult();

        } catch (Exception e) {

            LOGGER.error("Fail to find the chat room name", e);
            return null;
        }
        return result;
    }

    @Override
    public ChatRoom findChatRoom(Integer chatroomId) {

        LOGGER.trace("In ChatDAO findChatRoom method");
        ChatRoom result;

        try {
            Query query = entityManager.createNativeQuery("select * from ChatRoom where chat_room_id = ?", ChatRoom.class);

            query.setParameter(1, chatroomId);

            LOGGER.trace("Execute get query");
            result = (ChatRoom) query.getSingleResult();

        } catch (Exception e) {

            LOGGER.error("Fail to find chat room!!", e);

            return null;
        }
        return result;
    }

    @Override
    public boolean insertMessage(String message, UserModel userModel, String chatRoomId) {

        LOGGER.trace("In ChatDAO insertMessage method");
        Date currentDate = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String formattedDate = sdf.format(currentDate);

        try {
            Query query = entityManager.createNativeQuery("insert into " + chatRoomId + MESSAGE_POSTFIX + "(content,time,senderId,sender) " +
                    "value ('" + message + "','" + formattedDate + "'," + userModel.getId() + ",'" + userModel.getName() + "');");
            LOGGER.trace("sql: {}", query.toString());

            int result = query.executeUpdate();
            LOGGER.debug("rows inserted", result);

            return true;

        } catch (Exception e) {

            LOGGER.error("Cannot insert message {}", message, e);

            throw new InsertErrorException("Cannot insert message" + message);
        }
    }

    @Override
    public List<UserModel> blurrySearchUsername(String input){

        LOGGER.trace("In ChatDAO blurrySearchUsername method");
        List<UserModel> result;

        try{
            String sql = "from user where username like '%" + input + "%'";
            LOGGER.trace("sql: {}", sql.toString());

            result = entityManager.createQuery(sql, UserModel.class).getResultList();

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return result;
    }

    @Override
    public void updateChatRoomName(UserModel userModel, Boolean admin, Integer chatRoomId, String name, Integer id){

        LOGGER.debug("In ChatDAO updateChatRoomName method");
        String tableName = userModel.getId() + CHAT_ROOM_NAME_POSTFIX;

        String sql = "update `" + tableName +
                "` set admin = ?" +
                ", chat_room_id = ?" +
                ", chat_room_name = ?" +
                ", last_modified = CURRENT_TIMESTAMP " +
                "where chat_room_name_id = ?";
        LOGGER.debug("SQL string: " + sql);

        try {
            Query query = entityManager.createNativeQuery(sql);

            query.setParameter(1, admin);
            query.setParameter(2, chatRoomId);
            query.setParameter(3, name);
            query.setParameter(4, id);

            LOGGER.trace("Executeupdate");
            query.executeUpdate();

        } catch (Exception e){

            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("fail to update chat room name");
        }
    }

    @Override
    public ChatRoom createNewChatRoom(){

        LOGGER.trace("In ChatDAO createNewChatRoom method");
        ChatRoom chatRoom = new ChatRoom(null, false);

        try{
            LOGGER.debug("Store the chatroom");
            entityManager.persist(chatRoom);
            LOGGER.trace("flush ChatRoom");
            entityManager.flush();

        } catch(Exception e){

            LOGGER.error("Fail to create new chat room!!!!!!!" , e);
        }
        return chatRoom;
    }

    @Override
    public void insertNewChatRoomName(ChatRoom chatRoom, Integer userId, String name){

        LOGGER.trace("In ChatDAO insertNewChatRoomName method");
        String sql = "insert into " + userId + CHAT_ROOM_NAME_POSTFIX + " (chat_room_id, admin, chat_room_name, last_modified) value (" + chatRoom.getId() + ",False,'" + name + "', CURRENT_TIMESTAMP);";
        LOGGER.debug("Insert new chat room name sql: " + sql);

        try{
            LOGGER.trace("Executeupdate");
            entityManager.createNativeQuery(sql).executeUpdate();

        } catch(Exception e){

            LOGGER.error("Fail to insert new chat room name", e);
        }
    }

    @Override
    public void insertChatRoomToUser(ChatRoomToUser chatRoomToUser){

        LOGGER.trace("In ChatDAO insertChatRoomToUser method");
        try{
            entityManager.persist(chatRoomToUser);

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
        }
    }

    @Override
    public List<ChatRoomToUser> findChatRoomToUserByChatRoom(Integer chatRoomId){

        LOGGER.trace("In ChatDAO findChatRoomToUserByChatRoom method");
        List<ChatRoomToUser> result;

        try{
            Query query = entityManager.createNativeQuery("select * from ChatRoomToUser where chat_room_id = ?", ChatRoomToUser.class);
            LOGGER.trace("sql: {}", query.toString());

            query.setParameter(1, chatRoomId);

            LOGGER.trace("Execute get query");
            result = query.getResultList();

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return result;
    }

    @Override
    public List<ChatRoomToUser> findChatRoomToUserByUserID(Integer userId){

        LOGGER.trace("In ChatDAO findChatRoomToUserByUserID method");
        List<ChatRoomToUser> result;

        try{
            Query query = entityManager.createNativeQuery("select * from ChatRoomToUser where user_id = ?", ChatRoomToUser.class);
            LOGGER.trace("sql: {}", query.toString());

            query.setParameter(1, userId);

            LOGGER.trace("Execute get query");
            result = query.getResultList();

        } catch(Exception e){

            LOGGER.error(e.getMessage(), e);
            return null;
        }
        return result;
    }

    @Override
    public void deleteChatRoomName(Integer chatRoomNameId, Integer userId){

        LOGGER.trace("In ChatDAO deleteChatRoomName method");
        String sql = "delete from " + userId + CHAT_ROOM_NAME_POSTFIX+ " where chat_room_name_id = ?";

        try{
            Query query = entityManager.createNativeQuery(sql);
            LOGGER.trace("sql: {}", query.toString());

            query.setParameter(1, chatRoomNameId);

            LOGGER.trace("Executeupdate");
            query.executeUpdate();

        } catch (Exception e){

            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("fail to delete chat room name");
        }
    }

    public void createNemMessage(Integer chatRoomId){

        LOGGER.trace("In ChatDAO createNemMessage method");
        String sql = "create table " +  chatRoomId + MESSAGE_POSTFIX +
                " (message_id SERIAL primary key not null," +
                " time DATETIME not null DEFAULT CURRENT_TIMESTAMP," +
                " content TEXT not null," +
                " senderId int not null," +
                " sender varchar(225) not null," +
                "  FOREIGN KEY (senderId) REFERENCES user (user_id));";
        LOGGER.trace("SQL: " + sql);

        try{
            Query query = entityManager.createNativeQuery(sql);

            LOGGER.trace("Executeupdate");
            query.executeUpdate();

        } catch (Exception e){

            LOGGER.error(e.getMessage(), e);
            throw new RuntimeException("fail to create Nem Message table");
        }
    }
}