package com.example.capstone.login.service;

import com.example.capstone.login.model.UserModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
//@EnableTransactionManagement
public class UserLoginService implements ILoginService {

    @Override
    public UserModel getUser(String userName){

        return new UserModel(1, "test1", "0841027", "tester", true);
    }
}
