package com.psugv.capstone.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class MessageController {
    @GetMapping("/getMessage")
    public String getMessage(@RequestParam(defaultValue = "") String chatRoom,
                             @RequestParam(defaultValue = "") String message,
                             @RequestParam(defaultValue = "") String sender) {

        // Log the request
        System.out.println("Received message request: chatRoom=" + chatRoom + ", message=" + message + ", sender=" + sender);

        // Handle the request logic
        // ...

        // Return a view name or model object
        return "message";
    }
}
