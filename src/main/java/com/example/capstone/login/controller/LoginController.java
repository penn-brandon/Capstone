package com.example.capstone.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
@SessionAttributes({"uesrModel"})
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
    public String redirectToMainPage(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserModel loginModel = loginService.getUser(username);

        model.addAttribute("loginModel", loginModel);
        return "/index";
    }

    @GetMapping(path="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/login";
    }

    @GetMapping(path="/pages")
    public String loginPage() {
        return "pages";

    }
}