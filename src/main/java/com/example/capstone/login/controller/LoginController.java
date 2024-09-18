package com.example.capstone.login.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.capstone.login.model.UserModel;
import com.example.capstone.login.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;


@Controller
@SessionAttributes({"userModel"})
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @GetMapping(path="/")
    public String mainPagePath() {
        return "redirect:/index";
    }

    @GetMapping(path="/login")
    public String loginPagePath() {
        return "/login";
    }

    @GetMapping(path="/index")
    public String toIndexPage() {
        return "/index";
    }

    @GetMapping(path="/chat")
    public String toChatPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserModel userModel = loginService.getUser(username);

        model.addAttribute("userModel", userModel);
        return "/index";
    }

    @GetMapping(path="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){

        	new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/signin";
    }
/*
    @GetMapping(path="/pages")
    public String loginPage() {
        return "pages";
    }
 */
}