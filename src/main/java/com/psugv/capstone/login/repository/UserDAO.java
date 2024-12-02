package com.psugv.capstone.login.repository;

import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.exception.NoQueryResultException;
import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * This data access object class is for user operation related to DB.
 * Author: Chuan Wei
 */
@Repository
public class UserDAO implements IUserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    @Autowired
    EntityManager entityManager;


    public UserModel getUserByUsername(String userName) {

        LOGGER.trace("In UserDAO.getUserByUsername method");
        UserModel um;

        try {
            Query user_query = entityManager.createNativeQuery("select * from user where userName = ?", UserModel.class);
            LOGGER.trace("sql: {}", user_query.toString());

            user_query.setParameter(1, userName);

            LOGGER.trace("Execute get query");
            um = (UserModel) user_query.getSingleResult();

        } catch (NoResultException e) {

            LOGGER.error("Fail to load user by user name!!! Username: {}", userName, e);
            throw new NoQueryResultException("username: " + userName + " not found");
        }
        return um;
    }

    @Override
    public UserModel registration(UserModel user) {

        LOGGER.trace("In UserDAO.registration method");
        try {
            LOGGER.trace("Execute persist query");
            entityManager.persist(user);
            LOGGER.trace("Execute flush");
            entityManager.flush();

        } catch (Exception e) {

            LOGGER.error("Fail to register user!!!", e);
            throw new InsertErrorException("Registration failure!!!");
        }
        return user;
    }

    @Override
    public void createChatRoomName(Integer userId) {

        LOGGER.trace("In UserDAO createChatRoomName method");
        try {
            String sql = "CREATE TABLE " + userId.toString() + "_ChatRoomName ( " + "chat_room_name_id SERIAL PRIMARY KEY NOT NULL," + "chat_room_id INT NOT NULL," + "admin BOOLEAN NOT NULL," + "chat_room_name TEXT NOT NULL," + "last_modified DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP," + "FOREIGN KEY (chat_room_id) REFERENCES ChatRoom (chat_room_id));";
            LOGGER.debug("Sql: {}", sql);

            LOGGER.trace("Execute update query");
            entityManager.createNativeQuery(sql).executeUpdate();

        } catch (Exception e) {

            LOGGER.error(e.getMessage(), e);
            throw new NoQueryResultException("Fail to create chat room name name!!!");
        }
    }

    @Override
    public UserModel findUserById(Integer userId) {

        LOGGER.trace("In UserDAO.findUserById method");
        UserModel userModel;

        try {
            Query query = entityManager.createNativeQuery("select * from user where user_id = ?", UserModel.class);
            LOGGER.trace("sql: {}", query.toString());

            query.setParameter(1, userId.toString());

            LOGGER.trace("Execute get query");
            userModel = (UserModel) query.getSingleResult();

        } catch (Exception e) {

            LOGGER.error("Fail to find user by Id!!!", e);
            throw new InsertErrorException("Registration failure!!!");
        }
        return userModel;
    }
}
