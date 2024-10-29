package com.psugv.capstone.login.service;

import com.psugv.capstone.login.model.UserModel;

import java.util.Map;


public interface ILoginService {

	//public UserModel getUser(UserModel user);

	public UserModel getUserByUsername(String userName);

	public boolean registration(Map<String, String> inputMap);
}
