package com.example.capstone.login.service;

import com.example.capstone.login.model.UserModel;


public interface ILoginService {
    UserModel getUser(String userName);

}
