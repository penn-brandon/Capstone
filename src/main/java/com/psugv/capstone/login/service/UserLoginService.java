package com.psugv.capstone.login.service;

import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.repository.IUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.psugv.capstone.login.model.UserModel;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
@EnableTransactionManagement
public class UserLoginService implements ILoginService {

    private final static String NORMAL_AUTHORITIy = "NORMAL";

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginService.class);

    @Autowired
    IUserDAO userDAO;

    @Override
    public UserModel getUserByUsername(String username){

        return userDAO.getUserByUsername(username);
    }

    @Override
    public boolean registration(Map<String, String> inputMap){

        try{
            UserAuthorityModel authority = userDAO.getAuthority(NORMAL_AUTHORITIy);
            LOGGER.info(authority.toString());
            Set<UserAuthorityModel> authoritiesSet = new HashSet<UserAuthorityModel>();

            authoritiesSet.add(authority);

            userDAO.registration(inputMap, authoritiesSet);

        } catch (Exception e){

            LOGGER.error(e.getMessage());
            throw new InsertErrorException("Registration failed");
        }
        return true;
    }
}