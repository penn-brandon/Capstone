package com.psugv.capstone.chat.controller;

import com.psugv.capstone.chat.service.IChatService;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.service.ILoginService;
import com.psugv.capstone.util.ChatServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes({"loginModel", "chatRoom"})
@Component
public class ChatController {

    @Autowired
    IChatService chatService;

    @Autowired
    ILoginService loginService;

    @GetMapping(path="/chat")
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
     *
     * @param inputMap
     * key: message, value: message send by user
     * key: room, value: chat room ID
     * @return
     */
    @PostMapping(path="/send", consumes="application/json")
    public @ResponseBody String sendMessage (@RequestBody Map<String, String> inputMap, UserModel userModel) {

        Boolean result = chatService.sendMessage(inputMap.getOrDefault("message", null), inputMap.getOrDefault("room", null), userModel);

        return result.toString();
    }

    @PostMapping(path="/select")
    public @ResponseBody Map<String, String> selectChatBox (@RequestBody String chatRoomID, UserModel userModel){

        Map<String, String> map = new HashMap<>();
        chatService.selectChatRoom(chatRoomID, userModel);

        return map;
    }
}
