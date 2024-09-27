package com.psugv.capstone.login.repository;

import com.psugv.capstone.login.model.UserModel;

public interface IUserDAO {

    public UserModel getUserByUsername(String username);
}
