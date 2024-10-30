package com.psugv.capstone.repository;

import com.psugv.capstone.chat.repository.IChatDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ChatDAOTest {

    @Autowired
    private IChatDAO chatDAO;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void analyzeGetAllChatroomName() {

        assertEquals(1, chatDAO.getAllChatroomName(1).size());
    }

    @Test
    public void analyzeLoadHistoryMessage() {

        assertEquals(2, chatDAO.loadHistoryMessage(1).size());
    }

    @Test
    public void analyzeFindChatRoomName() {

        assertNotEquals(chatDAO.findChatRoomName(2, 1), chatDAO.findChatRoomName(1, 2));
    }

    @Test
    public void analyzeFindChatRoom() {

        assertEquals(chatDAO.findChatRoom(1), chatDAO.findChatRoom(1));
    }
}
