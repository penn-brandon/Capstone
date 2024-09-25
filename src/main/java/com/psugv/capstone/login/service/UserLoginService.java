package com.psugv.capstone.login.service;

import com.psugv.capstone.login.repository.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.psugv.capstone.login.model.UserModel;

@Service
@Transactional
@EnableTransactionManagement
public class UserLoginService implements ILoginService {

    @Autowired
    IUserDAO loginDAO;

    @Override
    public UserModel getPasswordByUsername(String userName){

        return loginDAO.getUser();
    }
}
