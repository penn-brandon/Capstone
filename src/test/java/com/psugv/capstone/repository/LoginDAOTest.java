package com.psugv.capstone.repository;

import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.repository.IUserDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
@SpringBootTest
public class LoginDAOTest {

    @Autowired
    private IUserDAO userDAO;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void analyzeLogin() {

        assertEquals(userDAO.getUserByUsername("weichuan"), userDAO.getUserByUsername("weichuan"));
        assertEquals(userDAO.getUserByUsername("weichuan"), userDAO.getUserByUsername("robot"));
    }

    @Test
    public void analyzeRegistration(){

        UserAuthorityModel authority = new UserAuthorityModel(null, "NORMAL", null);

        Set<UserAuthorityModel> authoritiesSet = new HashSet<UserAuthorityModel>();

        authoritiesSet.add(authority);

        UserModel newUser = new UserModel(null, "AAAAAAAAAAAAA", "1234", "TESTAAA", null, "Male", true, null);

        authority.setUserModel(newUser);

        newUser.setAuthorities(authoritiesSet);

        UserModel search = userDAO.registration(newUser);

        assertEquals(search.getId(), 5);
    }

    @Test
    public void analyzeCreateChatRoomName (){

        userDAO.createChatRoomName(100);
    }

    @Test
    public void analyzeFindUserById(){

        userDAO.findUserById(2);
    }
}*/