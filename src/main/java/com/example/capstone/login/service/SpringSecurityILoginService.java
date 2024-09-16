package com.example.capstone.login.service;

import com.example.capstone.login.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@EnableTransactionManagement
public class SpringSecurityILoginService implements ILoginService {

    @Override
    public User getUser(String userName){

        return new User();
    }
}
