package com.example.capstone.security;

import java.util.ArrayList;
import java.util.Collection;

import java.util.List;

import com.example.capstone.login.model.UserModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class SecurityLogin implements UserDetails {

    private static final long serialVersionUID = -6690946490872875352L;

    private UserModel user;

    public SecurityLogin(UserModel user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
//		return loginModel.getIsAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
		return this.user.getIsAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getIsEnable();
    }
}
