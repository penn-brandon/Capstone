package com.psugv.capstone.login.controller;

import com.psugv.capstone.login.model.UserModel;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.psugv.capstone.login.service.ILoginService;

@Controller
@SessionAttributes({"userModel"})
@Component
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @GetMapping(path="/")
    public String mainPagePath() {
    	System.out.println("In controller");
    	return "redirect:/index";
    }

    @GetMapping (path="/login")
    public String loginPagePath() {
        return "/open/login";
    }

    @GetMapping (path="/tosql")
    public String tosql() {
        return "/error";
    }

    @GetMapping(path="/index")
    public String toIndexPage() {
        return "/open/index";
    }

    @GetMapping(path="/error")
    public String toErrorPage() {
        return "/open/error";
    }


/*
    @GetMapping(path="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){

        	new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/index";
    }
*/
}