package com.psugv.capstone.repository.login;

import com.psugv.capstone.login.repository.IUserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class LoginDAOTest {

    @Autowired
    private IUserDAO useDAO;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    /**
     * This method is designed to test toMil method in the UnitsConvertor class.
     */
    public void analyzeLogin() {

        //assertEquals(1 * 39.3701, UnitsConvertor.toMil(1, "mm"));


    }
}
