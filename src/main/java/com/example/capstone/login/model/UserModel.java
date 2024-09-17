package com.example.capstone.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity(name = "user")
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

    @JsonIgnore
    @OneToMany(mappedBy="user",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
    Set<UserAuthenticationModel> authenticationModelSet;

    //List<ChatRoom> charRooms;

    public UserModel(Integer id, String username, String password) {

        user_id = id;
        this.username = username;
        this.password = password;
        this.date_of_creation = new Date();
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

    public Set<UserAuthenticationModel> getAuthenticationModelSetpermission() {
        return authenticationModelSet;
    }

    public void setAuthenticationModelSetpermission(Set<UserAuthenticationModel> authenticationModelSetpermission) {
        this.authenticationModelSet = authenticationModelSetpermission;
    }
}

