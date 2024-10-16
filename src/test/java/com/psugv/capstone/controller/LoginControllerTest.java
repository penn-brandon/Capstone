package com.psugv.capstone.controller;

import com.psugv.capstone.login.controller.LoginController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.Collection;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LoginControllerTest {


    private LoginController loginController = new LoginController();

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

        assertEquals("redirect:/index", loginController.mainPagePath());
        assertEquals("/open/login", loginController.loginPagePath());
        assertEquals("/open/index", loginController.toIndexPage());
        assertEquals("/open/error", loginController.toErrorPage());
    }
}
