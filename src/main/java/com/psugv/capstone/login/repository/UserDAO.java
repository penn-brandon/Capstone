package com.psugv.capstone.login.repository;

import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.EntityManager;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    EntityManager entityManager;

    @Value("${initialize.db.tables}")
    private String initializeTablesFilePath;

    public UserModel getUserByUsername(String userName){

        /*
        return entityManager.createQuery("from user where userName = :userName", UserModel.class).setParameter("userName", userName).getSingleResult();
        */
        return new UserModel(1, "weichuan", "19951027", "male", true);
    }
}
