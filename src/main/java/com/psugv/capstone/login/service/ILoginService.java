package com.psugv.capstone.login.service;

import com.psugv.capstone.login.model.UserModel;


public interface ILoginService {

	//public UserModel getUser(UserModel user);


	public UserModel getUserByUsername(String userName);
}
