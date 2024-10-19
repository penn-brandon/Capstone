package com.psugv.capstone.service;

import com.psugv.capstone.chat.service.IChatService;
import com.psugv.capstone.login.repository.IUserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChatServiceTest {


    @Autowired
    private IChatService chatService;

    @Autowired
    private IUserDAO userDAO;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void analyzeSendMessage() {

        assertEquals(Boolean.TRUE ,chatService.sendMessage("Hi!", userDAO.getUserByUsername("weichuan"), "1"));
    }

    @Test
    public void analyzeSelectChatRoom() {

        assertEquals(chatService.selectChatRoom("1", userDAO.getUserByUsername("robot")), chatService.selectChatRoom("1", userDAO.getUserByUsername("weichuan")));
    }

    @Test
    public void analyzeLoadHistoryMessage() {

        assertEquals(2, chatService.loadHistoryMessage(1).size());
    }

    @Test
    public void analyzeGetAllChatRoomName() {

        assertEquals(1, chatService.getAllChatRoomName(userDAO.getUserByUsername("weichuan")).size());
    }
}
