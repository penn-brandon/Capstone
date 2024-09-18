package com.example.capstone.login.model;


import org.springframework.stereotype.Component;

//@Entity(name = "permission")
//@Table(name = "permission")
@Component("authorities")
public class UserAuthorityModel {

    //@Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String authorityName;

    //@ManyToOne
    //@JoinColumn(name = "user_id")
    private UserModel userModel;

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

    public UserModel getLoginModel() {
        return userModel;
    }

    public void setLoginModel(UserModel userModel) {
        this.userModel = userModel;
    }
}
