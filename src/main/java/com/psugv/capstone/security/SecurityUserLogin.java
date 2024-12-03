package com.psugv.capstone.security;

import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * This class implements userdetails of spring security to gathering all required fields.
 * Author: Chuan Wei
 */
@Component
@Qualifier("SecurityUserLogin")
public class SecurityUserLogin implements UserDetails {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityUserLogin.class);

    @Serial
    private static final long serialVersionUID = -6690946490872875352L;

    private final UserModel user;

    public SecurityUserLogin(UserModel user) {
        this.user = user;
        LOGGER.debug(this.user.toString());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> authorities = new HashSet<>();

        Set<UserAuthorityModel> auSet = user.getAuthorities();

        for (UserAuthorityModel au : auSet) {
            authorities.add(new SimpleGrantedAuthority(au.getAuthorityName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnable();
        //return true;
    }
}
