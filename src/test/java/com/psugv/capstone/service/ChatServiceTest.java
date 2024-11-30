package com.psugv.capstone.service;

import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.repository.IChatDAO;
import com.psugv.capstone.chat.service.IChatService;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.repository.IUserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
/*
@SpringBootTest
public class ChatServiceTest {

    private final static Logger LOGGER = LoggerFactory.getLogger(ChatServiceTest.class);

    @Autowired
    private IChatService chatService;

    @Autowired
    private IUserDAO userDAO;

    @Autowired
    private IChatDAO chatDAO;
    @Autowired
    private ChatRoomName chatRoomName;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void analyzeSendMessage() {

        assertEquals(Boolean.TRUE, chatService.sendMessage("Hi!", userDAO.getUserByUsername("weichuan"), "1"));
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

        assertEquals(3, chatService.getAllChatRoomName(userDAO.getUserByUsername("weichuan")).size());
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

    @Test
    public void analyzeSearchUser() {

        assertEquals(1, chatService.searchUser("rob").size());
    }

    @Test
    public void analyzeCreateChatRoom() {

        UserModel userModel = userDAO.findUserById(1);

        Map<String, String> map = new HashMap<>();

        map.put("id", "2");

        map.put("username", "robot");

        map.put("name", "Bob the builder");

        ChatRoomName crn = chatService.createChatRoom(map, userModel);

        assertEquals(1, crn.getChatRoom().getId());

        map.put("id", "4");

        map.put("username", "purdue");

        map.put("name", "George the Monster");

        crn = chatService.createChatRoom(map, userModel);

        assertEquals(4, crn.getChatRoom().getId());
    }

    @Test
    public void analyzeDeselectChatRoom() {

        chatService.deselectChatRoom(userDAO.getUserByUsername("weichuan"));
    }

    @Test
    public void analyzeAddUserToChatRoom() {

        UserModel userModel = userDAO.findUserById(1);

        Map<String, String> map = new HashMap<>();

        map.put("id", "4");

        map.put("username", "purdue");

        map.put("name", "George the Monster");

        map.put("chatroom", "1");

        ChatRoomName chatRoomName = chatService.addUserToChatRoom(map, userModel);

        assertEquals(1, chatRoomName.getChatRoom().getId());

        map.put("id", "2");

        map.put("username", "robot");

        map.put("name", "Bob the builder");

        map.put("chatroom", "4");

        chatRoomName = chatService.addUserToChatRoom(map, userModel);

        assertEquals(4, chatRoomName.getChatRoom().getId());
    }
}
*/