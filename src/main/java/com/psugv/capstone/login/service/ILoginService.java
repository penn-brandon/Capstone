package com.psugv.capstone.login.service;

import com.psugv.capstone.login.model.UserModel;

import java.util.Map;


public interface ILoginService {

    //public UserModel getUser(UserModel user);

    UserModel getUserByUsername(String userName);

    boolean registration(Map<String, String> inputMap);
}
