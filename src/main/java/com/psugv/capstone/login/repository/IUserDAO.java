package com.psugv.capstone.login.repository;

import com.psugv.capstone.login.model.UserModel;

public interface IUserDAO {

    UserModel getUserByUsername(String username);

    boolean registration(UserModel user);

    //public UserAuthorityModel insertAuthority(UserAuthorityModel authority);

    //public boolean registrateAuthority(UserAuthorityModel authority);
}
