package com.psugv.capstone.service;

import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.service.ILoginService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserLoginServiceTest {


        @Autowired
        private ILoginService loginService;

        @BeforeEach
        void setUp() throws Exception {
        }

        @AfterEach
        void tearDown() throws Exception {
        }

        @Test
        public void analyzeLogin() {
            assertEquals(loginService.getUserByUsername("weichuan"), loginService.getUserByUsername("weichuan"));
            assertNotEquals(loginService.getUserByUsername("weichuan"), loginService.getUserByUsername("robot"));
        }
}
