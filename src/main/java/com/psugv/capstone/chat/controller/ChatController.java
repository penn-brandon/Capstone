package com.psugv.capstone.chat.controller;

import com.psugv.capstone.chat.model.ChatRoomName;
import com.psugv.capstone.chat.service.IChatService;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.service.ILoginService;
import com.psugv.capstone.util.ChatServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes({"userModel", "chatRoom"})
@Component
public class ChatController {

    @Autowired
    IChatService chatService;

    @Autowired
    ILoginService loginService;

    @GetMapping(path = "/chat")
    public String toChatPage(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        System.out.println("Storing user to seesion: " + username);
        System.out.println("Storing user to seesion: " + authentication.getCredentials());
        UserModel userModel = loginService.getUserByUsername(username);
        model.addAttribute("userModel", userModel);

        return "/main/chat";
    }

    /**
     * @param inputMap key: message, value: message send by user
     *                 key: room, value: chat room ID
     * @return
     */
    @PostMapping(path = "/send", consumes = "application/json")
    public @ResponseBody String sendMessage(@RequestBody Map<String, String> inputMap) {

        if(inputMap.get("message") == null || inputMap.get("message").isEmpty()
                || inputMap.get("room") == null || inputMap.get("room").isEmpty()) {

            return "MESSAGE SENDING FAILURE";
        }

        Boolean result = chatService.sendMessage(inputMap.get("message"), inputMap.get("room"));

        return result.toString();
    }

    @PostMapping(path = "/select")
    public @ResponseBody Map<String, String> selectChatBox(@RequestBody String chatRoomID, UserModel userModel) {

        Map<String, String> map = new HashMap<>();
        chatService.selectChatRoom(chatRoomID, userModel);

        return map;
    }

    @GetMapping(path = "/loadAllChatRoomName", produces = "application/json")
    public @ResponseBody List<ChatRoomName> loadAllChatRoomName(UserModel userModel) {

        List<ChatRoomName> result = chatService.getAllChatRoomName(userModel);

        return result;
    }

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String greeting(String message) throws Exception {

        return "Hello, " + message + "!";
    }
}