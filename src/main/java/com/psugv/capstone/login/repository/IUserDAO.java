package com.psugv.capstone.login.repository;

import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;

import java.util.Map;
import java.util.Set;

public interface IUserDAO {

    public UserModel getUserByUsername(String username);

    public boolean registration(UserModel user);

    //public UserAuthorityModel insertAuthority(UserAuthorityModel authority);

    //public boolean registrateAuthority(UserAuthorityModel authority);
}
