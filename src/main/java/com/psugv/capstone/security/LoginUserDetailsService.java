package com.psugv.capstone.security;

import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.repository.IUserDAO;
import com.psugv.capstone.util.ChatServer;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * This class implements userdetailsservice of spring security to load user data by given username.
 *
 * Author: Chuan Wei
 */
@Service
@RequiredArgsConstructor
@Qualifier("LoginUserDetailsService")
public class LoginUserDetailsService implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginUserDetailsService.class);

    @Autowired
    private IUserDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        System.out.println("userName input: " + userName);

        UserModel user = userDAO.getUserByUsername(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User details not found for the user : " + userName);
        }
        return new SecurityUserLogin(user);
    }
}
