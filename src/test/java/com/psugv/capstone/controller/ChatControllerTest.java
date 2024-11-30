package com.psugv.capstone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.psugv.capstone.chat.model.ChatRoom;
import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    @Test
    public void analyzeToChatPage() throws Exception{

        mockMvc.perform(get("/chat")
                        .with(user("weichuan").password("1234")))
                .andExpect(status().isOk())
                .andExpect(view().name("/main/chat"));
    }

    @Test
    public void analyzeSendMessage() throws Exception {

        Map<String, String> map = new HashMap<>();

        map.put("message", "testtest");

        map.put("room", "1");

        String inputMapJson = objectMapper.writeValueAsString(map);

        String result = Boolean.TRUE.toString();

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userModel", um1);

        mockMvc.perform(post("/send")
                        .session(session)
                        .content(inputMapJson)
                        .contentType("application/json")
                        .with(user("weichuan").password("1234")))
                .andExpect(status().isOk())
                .andExpect(content().string(result));
    }

    @Test
    public void analyzeSelectChatBox() throws Exception{

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userModel", um1);

        mockMvc.perform(get("/select")
                        .header("chatRoomID", "1")
                        .session(session)
                        .with(user("weichuan").password("1234")))
                .andExpect(view().name("redirect:/loadMessage"));
    }

    @Test
    public void analyzeLoadHistoryMessage() throws Exception{

        chatRoomName.setChatRoom(cr);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("chatRoomName", chatRoomName);

        mockMvc.perform(get("/loadMessage")
                        .session(session)
                        .with(user("weichuan").password("1234")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].content").value("testtest"));
    }

    @Test
    public void analyzeLoadAllChatRoomName() throws Exception{

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userModel", um1);

        mockMvc.perform(get("/loadAllChatRoomName")
                        .session(session)
                        .with(user("weichuan").password("1234")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0][2]").value("false"));
    }

    @Test
    public void analyzeSearchUser() throws Exception{

        mockMvc.perform(post("/searchUsers")
                        .header("username", "ich")
                        .with(user("weichuan").password("1234")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));
    }

    @Test
    public void analyzeCreateNewChatRoom() throws Exception{

        Map<String, String> map = new HashMap<>();

        map.put("id", "3");

        map.put("name", "Freddy The Creator");

        map.put("username", "freddy");

        String inputMapJson1 = objectMapper.writeValueAsString(map);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userModel", um1);

        mockMvc.perform(post("/createNewChatRoom")
                        .content(inputMapJson1)
                        .contentType("application/json")
                        .session(session)
                        .with(user("weichuan").password("1234")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chatRoom.id").value("4"));

        map.put("id", "2");

        map.put("username", "robot");

        map.put("name", "Bob the builder");

        String inputMapJson2 = objectMapper.writeValueAsString(map);

        mockMvc.perform(post("/createNewChatRoom")
                        .content(inputMapJson2)
                        .contentType("application/json")
                        .session(session)
                        .with(user("weichuan").password("1234")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chatRoomName").value("Bob the builder"));

    }

    @Test
    public void analyzeAddUserToChatRoom() throws Exception{

        Map<String, String> map = new HashMap<>();

        map.put("id", "4");

        map.put("name", "George the Monster");

        map.put("username", "purdue");

        map.put("chatroom", "1");

        String testMapJson = objectMapper.writeValueAsString(map);

        System.out.println(testMapJson);

        MockHttpSession session = new MockHttpSession();
        session.setAttribute("userModel", um1);

        mockMvc.perform(post("/addUserToChatRoom")
                        .content(testMapJson)
                        .contentType("application/json")
                        .session(session)
                        .with(user("weichuan").password("1234")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("3"));
    }

    ObjectMapper objectMapper = new ObjectMapper();

    ChatRoom cr = new ChatRoom(1, false);

    ChatRoomName chatRoomName = new ChatRoomName(1, false, null, "Test", new Date());

    UserModel um1 = new UserModel(1, "weichuan", "1234", "Chuan Wei", null, "male", true, new HashSet<UserAuthorityModel>());;
}
