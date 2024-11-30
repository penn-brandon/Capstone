package com.psugv.capstone.util;

import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UtilTest {

    @Autowired
    private ChatServer chatServer;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    UserModel um1 = new UserModel(1, "weichuan", "1234", "Chuan Wei", null, "male", true, new HashSet<UserAuthorityModel>());

    UserModel um2 = new UserModel(1, "robot", "1234", "Bob The Builder", null, "male", true, new HashSet<UserAuthorityModel>());

    ChatRoom cr = new ChatRoom(1, false);

    ChatRoomName chatRoomName = new ChatRoomName(1, false, null, "Test", new Date());

    @Test
    public void testChatServer() throws InterruptedException {

        ChatServer.removeFromOnlineUserPool(1);

        assertFalse(ChatServer.alreadyLogin(1));

        ChatServer.loginCheckin(1);

        assertTrue(ChatServer.alreadyLogin(1));

        ChatServer.removeFromOnlineUserPool(1);

        assertFalse(ChatServer.alreadyLogin(1));

        ChatServer.loginCheckin(1);

        chatRoomName.setChatRoom(cr);

        Thread t2 = new Thread(() -> {

            MessageListener messageListener2 = new MessageListener(cr, chatRoomName, um2, messagingTemplate);

            ChatServer.updateOnlineUserPool(2,1, messageListener2);
        });

        Thread t1 = new Thread(() -> {

            MessageListener messageListener = new MessageListener(cr, chatRoomName, um1, messagingTemplate);

            ChatServer.updateOnlineUserPool(1,1, messageListener);
        });

        t2.start();

        t1.start();

        assertTrue(ChatServer.alreadyLogin(1));

        Thread t3 = new Thread(() -> {

            ChatServer.sentMessage("Test", 1,1,"Chuan Wei");
        });

        t3.start();

        Thread t4 = new Thread(() -> {

            ChatServer.removeFromOnlineUserPool(1);

            ChatServer.removeFromOnlineUserPool(1);

            ChatServer.removeFromOnlineUserPool(2);

            assertFalse(ChatServer.alreadyLogin(1));
        });

        t4.start();
    }

    @Test
    public void testUtility() {

        List<Integer> l1 = new ArrayList<Integer>();

        List<Integer> l2 = new ArrayList<Integer>();

        for(int i = 0; i < 10;i++){

            l1.add(i);

            l2.add(i * 2);
        }

        List<Integer> result = Utility.commonIdComparator(l1, l2);

        assertEquals(5, result.size());

        l1.clear();

        List<Integer> result2 = Utility.commonIdComparator(l1, l2);

        assertEquals(0, result2.size());

        for(int i = 0; i < 15;i++){

            l1.add(i);
        }

        List<Integer> result3 = Utility.commonIdComparator(l1, l2);

        assertEquals(8, result3.size());
    }
}
