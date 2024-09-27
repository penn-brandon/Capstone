package com.psugv.capstone.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
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