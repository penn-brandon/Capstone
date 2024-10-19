package com.psugv.capstone.repository;

import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.repository.IUserDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}