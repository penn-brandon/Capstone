package com.psugv.capstone.service;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;

import com.psugv.capstone.chat.repository.IChatDAO;
import com.psugv.capstone.chat.service.IChatService;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.repository.IUserDAO;
import com.psugv.capstone.util.MessageListener;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ChatServiceTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ChatServiceTest.class);

    @Autowired
    private IChatService chatService;

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private IChatDAO chatDAO;

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

        assertEquals("Robot", chatService.selectChatRoom("1", userDAO.getUserByUsername("weichuan")).getChatRoomName());
    }

    @Test
    public void analyzeLoadHistoryMessage() {

        assertEquals(3, chatService.loadHistoryMessage(1).size());
    }

    @Test
    public void analyzeGetAllChatRoomName() {

        assertEquals(1, chatService.getAllChatRoomName(userDAO.getUserByUsername("weichuan")).size());
    }

    @Test
    public void analyzeSendUpdate() {

        UserModel userModel = userDAO.getUserByUsername("weichuan");

        LOGGER.debug("Start multithread testing.");
        Thread t1 = new Thread(() -> {

            chatService.selectChatRoom("1", userModel);
        });

        LOGGER.debug("t1 starts");
        t1.start();

        UserModel sender = userDAO.getUserByUsername("robot");

        Thread t2 = new Thread(() -> {

            //assertEquals(Boolean.TRUE, chatService.sendMessage("Test123Test123!!", userModel, "1"));
            chatService.sendMessage("Test123Test123!!", sender, "1");
        });

        LOGGER.debug("t2 starts");
        t2.start();

        LOGGER.info("analyzeSendUpdate is done");
    }

}
