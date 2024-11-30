package com.psugv.capstone.repository;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.model.ChatRoomToUser;
import com.psugv.capstone.chat.repository.IChatDAO;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.repository.IUserDAO;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
/*
@SpringBootTest
public class ChatDAOTest {

    @Autowired
    private IChatDAO chatDAO;

    @Autowired
    private IUserDAO userDAO;

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

    @Test
    public void analyzeInsertMessage() {

        UserModel um = userDAO.findUserById(1);

        assertEquals(true, chatDAO.insertMessage("test?test!", um, "1"));
    }

    @Test
    public void analyzeBlurrySearchUsername() {

        assertEquals(1, chatDAO.blurrySearchUsername("rob"));
    }

    @Test
    public void analyzeUpdateChatRoomName() {

        ChatRoomName crn = chatDAO.findChatRoomName(1,1);

        UserModel um = userDAO.findUserById(1);

        chatDAO.updateChatRoomName(um, crn.getAdmin(), crn.getChatRoom().getId(), crn.getChatRoomName(), crn.getId());
    }

    @Test
    public void analyzeCreateNewChatRoom() {

        ChatRoom cr = chatDAO.createNewChatRoom();

        assertEquals(4, cr.getId());
    }

    @Test
    public void analyzeInsertNewChatRoomName() {

        ChatRoom cr = chatDAO.createNewChatRoom();

        chatDAO.insertNewChatRoomName(cr, 1, "Test Room");
    }

    @Test
    public void analyzeInsertChatRoomToUser() {

        UserModel um = userDAO.findUserById(1);

        ChatRoom cr = chatDAO.createNewChatRoom();

        ChatRoomToUser crtu = new ChatRoomToUser(null, um, cr);

        chatDAO.insertChatRoomToUser(crtu);
    }

    @Test
    public void analyzeFindChatRoomToUserByChatRoom() {

        assertEquals(false, chatDAO.findChatRoomToUserByChatRoom(1).isEmpty());
    }

    @Test
    public void analyzefindChatRoomToUserByUserID() {

        assertEquals(false, chatDAO.findChatRoomToUserByUserID(1).isEmpty());
    }
}*/
