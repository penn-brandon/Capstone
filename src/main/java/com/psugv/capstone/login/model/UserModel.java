package com.psugv.capstone.login.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity(name = "user")
@Table(name = "user")
@Component("user")
public class UserModel implements Serializable {

    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Setter
    @Getter
    private String username;

    @Setter
    @Getter
    @JsonIgnore
    private String password;

    @Setter
    private Date date_of_creation;

    @Getter
    @Setter
    private String name;

    @Setter
    @Getter
    private String gender;

    @Setter
    @Getter
    @JsonIgnore
    @OneToMany(mappedBy = "userModel", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<UserAuthorityModel> authorities;

    @JsonIgnore
    @Column(name = "is_Enable")
    private Boolean isEnable;

    public UserModel(Integer id, String username, String password, String name, Date date, String gender, Boolean isEnable, Set<UserAuthorityModel> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.date_of_creation = date == null ? new Date() : date;
        this.gender = gender;
        this.isEnable = isEnable;
        this.authorities = authorities;
    }

    public UserModel() {
    }

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    @Override
    public boolean equals(Object object) {
        UserModel user = (UserModel) object;
        return Objects.equals(this.id, user.id) && this.username.equals(user.username);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
