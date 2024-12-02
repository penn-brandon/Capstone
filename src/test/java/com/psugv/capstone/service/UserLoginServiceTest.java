package com.psugv.capstone.service;

import com.psugv.capstone.exception.NoQueryResultException;
import com.psugv.capstone.login.service.ILoginService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserLoginServiceTest {

    @Autowired
    private ApplicationContext applicationContext;

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

        NoQueryResultException e = assertThrows(NoQueryResultException.class, ()->loginService.getUserByUsername("bot"));
    }

    @Test
    public void analyzeRegistration() {

        Map<String, String> map = new HashMap<>();

        map.put("username", "brandon");

        map.put("password", "123456");

        map.put("name", "popato");

        map.put("gender", "Male");

        assertEquals(true, loginService.registration(map));
    }
}
