package com.psugv.capstone.login.repository;

import com.psugv.capstone.login.model.UserModel;
import jakarta.persistence.EntityManager;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO implements IUserDAO {

    @Autowired
    EntityManager entityManager;

    public UserModel getPasswordByUsername(String userName){

        ScriptRunner scriptRunner = 

        return entityManager.find(UserModel.class, pk);
    }
}
