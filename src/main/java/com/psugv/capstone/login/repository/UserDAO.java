package com.psugv.capstone.login.repository;

import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Map;

@Repository
public class UserDAO implements IUserDAO {
/*
    @Autowired
    EntityManager entityManager;
*/

    public UserModel getUserByUsername(String userName){

        /*
        return entityManager.createQuery("from user where userName = :userName", UserModel.class).setParameter("userName", userName).getSingleResult();
        */

        System.out.println("Return user obj");
        UserModel um = new UserModel(1, "weichuan", "19951027", "male", true);
        UserAuthorityModel uam = new UserAuthorityModel();
        uam.setAuthorityName("NORMAL");
        uam.setId(1);
        uam.setLoginModel(um);
        HashSet<UserAuthorityModel> set =  new HashSet<>();
        set.add(uam);
        um.setAuthorities(set);
        return um;
    }
}
