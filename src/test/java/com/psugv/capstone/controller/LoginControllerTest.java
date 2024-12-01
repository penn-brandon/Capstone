package com.psugv.capstone.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void analyzeMainPagePath() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(view().name("redirect:/index"));
     }

    @Test
    public void analyzeLoginPagePath() throws Exception {

        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("/open/login"));
    }

    @Test
    public void analyzeSignupPagePath() throws Exception {

        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(view().name("/open/signup"));
    }

    @Test
    public void analyzeErrorPagePath() throws Exception {

        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("/open/error"));
    }

    @Test
    public void analyzeIndexPagePath() throws Exception {

        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("/open/index"));
    }

    @Test
    public void analyzeRegisterToApp() throws Exception{

        mockMvc.perform(post("/register")
                        .param("username", "test")
                        .param("password", "123")
                        .param("name", "robot test")
                        .param("gender", "Female"))
                //.andExpect(status().isOk())
                .andExpect(view().name("redirect:/login"));
    }
}
