package com.psugv.capstone.security;
/*
import com.example.capstone.login.model.UserModel;
import com.example.capstone.login.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginUserDetailsService implements UserDetailsService {


    @Autowired
    private ILoginService loginService;

    //@Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserModel user = loginService.getUser(userName);

        if (user == null) {
            throw new UsernameNotFoundException("User details not found for the user : " + userName);
        }
        return new SecurityUserLogin(user);
    }

}*/