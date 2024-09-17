package com.example.capstone.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

//@Entity(name = "user")
//@Table(name = "user")
@Component("user")
public class UserModel implements Serializable {

    //@Id
    //@GeneratedValue(strategy= GenerationType.AUTO)
    int user_id;

    String username;

    //@JsonIgnore
    String password;

    Date date_of_creation;

    //@JsonIgnore
    //@OneToMany(mappedBy="user",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    Set<UserAuthorityModel> authorities;

    //@JsonIgnore
    Boolean isEnable;

    //@JsonIgnore
    String role;

    //List<ChatRoom> charRooms;

    public UserModel(Integer id, String username, String password, String role, Boolean isEnable) {

        user_id = id;
        this.username = username;
        this.password = password;
        this.date_of_creation = new Date();
        this.role = role;
        this.isEnable = isEnable;
    }

    public UserModel() {}

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public Set<UserAuthorityModel> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<UserAuthorityModel> authorities) {
        this.authorities = authorities;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

