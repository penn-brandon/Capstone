package com.psugv.capstone.login.repository;

import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.exception.NoQueryResultException;
import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserDAO implements IUserDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDAO.class);

    @Autowired
    EntityManager entityManager;


    public UserModel getUserByUsername(String userName){

        UserModel um;
        try {
            //return entityManager.createQuery("from capstone.user where username = " + userName, UserModel.class).setParameter("userName", userName).getSingleResult();
            Query user_query = entityManager.createNativeQuery("select * from user where userName = ?", UserModel.class);
            user_query.setParameter(1, userName);

            um = (UserModel) user_query.getSingleResult();

        } catch (NoResultException e) {

            LOGGER.error("Fail to load user by user name!!!", e);
            return null;
        }

        return um;
    }

    @Override
    public boolean registration(Map<String, String> inputMap, Set<UserAuthorityModel> authorities){

        try {
            LOGGER.info(inputMap.toString());
            String username = inputMap.get("username");
            String password = inputMap.get("password");
            String name = inputMap.get("name");
            String gender = inputMap.get("gender");

            UserModel newUser = new UserModel(null, username, password, name, null, gender, true, authorities);

            entityManager.persist(newUser);

        } catch (Exception e) {

            LOGGER.error("Fail to register user!!!", e);
            throw new InsertErrorException("Registration failure!!!");
        }
        return true;
    }

    @Override
    public UserAuthorityModel getAuthority(String authorityName){

        UserAuthorityModel authority;

        try{
            authority = entityManager.createQuery("from authorities where authorityName = :authorityName", UserAuthorityModel.class)
                    .setParameter("authorityName", authorityName).getSingleResult();

        } catch (NoResultException e) {

            LOGGER.error("Fail to load authority by user name!!!", e);
            throw new NoQueryResultException("Cannot find given authrities name.");
        }
        return authority;
    }
}
