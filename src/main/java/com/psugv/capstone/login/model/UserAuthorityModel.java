package com.psugv.capstone.login.model;



import jakarta.persistence.*;
import org.springframework.stereotype.Component;


@Entity(name = "authorities")
@Table(name = "authorities")
@Component("authorities")
public class UserAuthorityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="authority_id")
    private Integer id;

    private String authorityName;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserModel userModel;

    public UserAuthorityModel() {}

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
