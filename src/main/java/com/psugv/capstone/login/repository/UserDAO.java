package com.psugv.capstone.login.repository;

import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Map;

@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    EntityManager entityManager;


    public UserModel getUserByUsername(String userName){

        UserModel um;
        try {
            //return entityManager.createQuery("from capstone.user where username = " + userName, UserModel.class).setParameter("userName", userName).getSingleResult();
            Query user_query = entityManager.createNativeQuery("select * from user where username = ?", UserModel.class);
            user_query.setParameter(1, userName);

            um = (UserModel) user_query.getSingleResult();

        } catch (NoResultException e) {

            e.printStackTrace();
            return null;
        }

        return um;
    }
}
