package com.psugv.capstone.login.repository;

import com.psugv.capstone.login.model.UserModel;

public interface IUserDAO {

    UserModel getUserByUsername(String username);

    boolean registration(UserModel user);

    void createChatRoomName(Integer userId);

    //public boolean registrationAuthority(UserAuthorityModel authority);
}
