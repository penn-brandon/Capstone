package com.psugv.capstone.login.model;


import jakarta.persistence.*;
import org.springframework.stereotype.Component;

/**
 * This is an entity class that represent authorization of users.
 *Author: Chuan Wei
 */
@Entity(name = "authorities")
@Table(name = "authorities")
@Component("authorities")
public class UserAuthorityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "authority_id")
    private Integer id;

    private String authorityName;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    public UserAuthorityModel() {
    }

    public UserAuthorityModel(Integer id, String authorityName, UserModel userModel) {

        this.id = id;
        this.authorityName = authorityName;
        this.userModel = userModel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public String toString() {

        return "Authority name is: " + this.authorityName;
    }
}
