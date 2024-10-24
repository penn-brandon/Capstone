package com.psugv.capstone.login.service;

import com.psugv.capstone.login.repository.IUserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.psugv.capstone.login.model.UserModel;

@Service
@Transactional
@EnableTransactionManagement
public class UserLoginService implements ILoginService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserLoginService.class);

    @Autowired
    IUserDAO userDAO;

    @Override
    public UserModel getUserByUsername(String username){

        return userDAO.getUserByUsername(username);
    }
}