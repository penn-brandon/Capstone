package com.psugv.capstone.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {
    @GetMapping(path="/getMessage")
    public String loginPagePath(HttpServletRequest request) {
        String chat_room = request.getParameter("chat_room");
        String message = request.getParameter("message");
        String sender = request.getParameter("sender");
    }
}
