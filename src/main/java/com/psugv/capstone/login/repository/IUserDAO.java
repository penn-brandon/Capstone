package com.psugv.capstone.login.repository;

import com.psugv.capstone.login.model.UserModel;


public interface IUserDAO {

    UserModel getUserByUsername(String username);

    UserModel registration(UserModel user);

    void createChatRoomName(Integer userId);

    UserModel findUserById(Integer userId);
}
