package com.psugv.capstone.service;

import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.service.ILoginService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        /**
         * This method is designed to test toMil method in the UnitsConvertor class.
         */
        public void analyzeLogin() {
            assertEquals(new UserModel(1, "weichuan", "19951027", "male", true), loginService.getUserByUsername("weuchuan"));
        }
}
