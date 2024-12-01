package com.psugv.capstone.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * This class implement the authemticationprovide of spring security to allow user to login.
 *
 * Author: Chuan Wei
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginAuthenticationProvider.class);

    @Autowired
    @Qualifier("LoginUserDetailsService")
    private UserDetailsService loginUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        UserDetails user = loginUserDetailService.loadUserByUsername(username);

        System.out.println("input password: " + pwd);
        System.out.println("uesr password: " + user.getPassword());

        if (passwordEncoder.matches(pwd, user.getPassword())) {
            System.out.println("Login successful");
            return new UsernamePasswordAuthenticationToken(username, pwd, user.getAuthorities());
        } else {

            System.err.println("Invalid username or password!");

            throw new BadCredentialsException("Invalid username or password!");
        }
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        //System.out.println(authenticationType.equals(UsernamePasswordAuthenticationToken.class));
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authenticationType);
    }

}
