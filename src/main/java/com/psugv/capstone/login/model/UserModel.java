package com.psugv.capstone.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

import org.springframework.stereotype.Component;

import jakarta.persistence.Id;

import java.io.Serializable;

//@Entity(name = "user")
//@Table(name = "user")
@Component("user")
public class UserModel implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    int user_id;

    String username;

    @JsonIgnore
    String password;

    Date date_of_creation;

    String gender;

    @JsonIgnore
    @OneToMany(mappedBy="user",fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    Set<UserAuthorityModel> authorities;

    @JsonIgnore
    Boolean isEnable;



    //@OneToMany(mappedBy="user",fetch= FetchType.EAGER, cascade=CascadeType.ALL)
    //List<ChatRoom> charRooms;

    public UserModel(Integer id, String username, String password, String gender, Boolean isEnable) {

        user_id = id;
        this.username = username;
        this.password = password;
        this.date_of_creation = new Date();
        this.gender = gender;
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


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object object) {

        UserModel user = (UserModel) object;
        if(this.user_id == user.user_id && this.username.equals(user.username)){
            return true;
        }
        return false;
    }
}
