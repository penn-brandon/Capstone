package com.example.capstone.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUserLogin implements UserDetails {


    private static final long serialVersionUID = -6690946490872875352L;

    private final LoginModel loginModel;

    public SecurityLogin(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(loginModel.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return loginModel.getEmployeePassword();
    }

    @Override
    public String getUsername() {
        return loginModel.getEmpNo();
    }

    @Override
    public boolean isAccountNonExpired() {
//		return loginModel.getIsAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//		return loginModel.getIsAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//		Date today = new Date();
//
//		String date = loginModel.getLastChangeCredentialsDate();
//		String year = date.substring(0, 4);
//		String month = date.substring(5, 7);
//		String day = date.substring(8);
//
//		Date lastChangeDate = new Date(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day));
//		Period difference = Period.between(lastChangeDate, today);
//		int dayDifference = difference.getDays();
//
//
//		return loginModel.getIsCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
        return loginModel.getIsEnable();
        //return true;
    }

}