package com.psugv.capstone.login.controller;


import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.login.service.ILoginService;
import com.psugv.capstone.util.ChatServer;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * This is controller class that deals with the login and registration
 * Author: Chuan Wei
 */
@Controller
@Component
@SessionAttributes("userModel")
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ILoginService loginService;

    @GetMapping(path = "/")
    public String mainPagePath() {

        return "redirect:/index";
    }

    @GetMapping(path = "/login")
    public String loginPagePath() {

        LOGGER.trace("loginPagePath");
        return "/open/login";
    }

    @GetMapping(path = "/index")
    public String toIndexPage() {

        LOGGER.trace("toIndexPage");
        return "/open/index";
    }

    @GetMapping(path = "/error")
    public String toErrorPage() {

        LOGGER.trace("toErrorPage");
        return "/open/error";
    }

    @GetMapping(path = "/signup")
    public String toSignupPage() {

        LOGGER.trace("toSignupPage");
        return "/open/signup";
    }

    @GetMapping(path = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response, @SessionAttribute("userModel") UserModel userModel) {

        LOGGER.trace("logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        ChatServer.removeFromOnlineUserPool(userModel.getId());
        return "redirect:/index";
    }

    @PostMapping(path = "/register")
    /*
     * Key: username, value: input username.
     * Key: password, value: input password.
     * Key: name, value: name displayed in the chat.
     * Key: gender, value: drop down list, should only have male, female, and other.
     */ public String registerToApp(@RequestParam String username, @RequestParam String password, @RequestParam String name, @RequestParam String gender) {

        LOGGER.trace("registerToApp controller called");
        boolean result;

        Map<String, String> map = new HashMap<>();

        map.put("username", username);
        map.put("password", password);
        map.put("name", name);
        map.put("gender", gender);

        LOGGER.debug(map.toString());

        try {
            result = loginService.registration(map);
            map.put("result", String.valueOf(result));

        } catch (InsertErrorException e) {

            LOGGER.error(e.getMessage());
            return "redirect:/signup";
        }
        if (!result) {

            LOGGER.debug("Registration failed");
            return "redirect:/signup";
        }

        LOGGER.trace("register succeed^_^");
        return "redirect:/login";
    }
}