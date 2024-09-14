package com.example.capstone.security;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {


    @Autowired
    private LoginService loginService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        LoginModel loginModel = loginService.getLoginModelByEmpNo(username);
        if (loginModel == null) {
            throw new UsernameNotFoundException("User details not found for the user : " + username);
        }
        return new SecurityLogin(loginModel);
    }

}