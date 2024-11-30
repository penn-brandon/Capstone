package com.psugv.capstone.login.service;

import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.exception.NoQueryResultException;
import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.repository.IUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * This class contains all business logic of users operation to their own data.
 *
 * Author: Chuan Wei
 */
@Service
@Transactional
@EnableTransactionManagement
public class UserLoginService implements ILoginService {

    private final static String NORMAL_AUTHORITY = "NORMAL";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginService.class);

    @Autowired
    IUserDAO userDAO;

    @Override
    public UserModel getUserByUsername(String username) {

        return userDAO.getUserByUsername(username);
    }

    @Override
    public boolean registration(Map<String, String> inputMap) {

        try{
            UserModel search;

            String username = inputMap.get("username");
            String password = inputMap.get("password");
            String name = inputMap.get("name");
            String gender = inputMap.get("gender");

            try {
                search = userDAO.getUserByUsername(username);

            } catch (NoQueryResultException e){

                LOGGER.debug("Username is available^_^");
                search = null;

            } finally{

                LOGGER.debug("Check that the finally block do not get skipped");
            }

            LOGGER.debug("Check that search result is: " + search);
            if(search != null){

                return false;
            }

            LOGGER.debug("create a new authrities");
            UserAuthorityModel authority = new UserAuthorityModel(null, NORMAL_AUTHORITY, null);
          
            Set<UserAuthorityModel> authoritiesSet = new HashSet<UserAuthorityModel>();

            authoritiesSet.add(authority);

            LOGGER.debug("Create a user model\n" + inputMap.toString());
            UserModel newUser = new UserModel(null, username, password, name, null, gender, true, null);

            authority.setUserModel(newUser);
            newUser.setAuthorities(authoritiesSet);

            LOGGER.debug("start inserting!!");
            search = userDAO.registration(newUser);

            userDAO.createChatRoomName(search.getId());

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new InsertErrorException("Registration failed");
        }
        return true;
    }
}