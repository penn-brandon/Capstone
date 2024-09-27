package com.psugv.capstone.login.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.psugv.capstone.login.service.ILoginService;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"userModel"})
public class LoginController {

    @Autowired
    private ILoginService loginService;

    @GetMapping(path="/")
    public String mainPagePath() {
    	System.out.println("In controller");
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


    @GetMapping(path="/signup")
    public String toSignupPage() {
        return "/signup";
    }

    @GetMapping(path="/error")
    public String toErrorPage() {
        return "/error";
    }

    @GetMapping(path="/chat")
    public String toChatPage(Model model) {
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //String username = authentication.getName();
       //UserModel userModel = loginService.getUser(username);

        //model.addAttribute("userModel", userModel);
        return "/chat";
    }

/*
    @GetMapping(path="/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){

        	new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "/login";
    }*/

/*
    @GetMapping(path="/pages")
    public String loginPage() {
        return "pages";
    }
 */
}