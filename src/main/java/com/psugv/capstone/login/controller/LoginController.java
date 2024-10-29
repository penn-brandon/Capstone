package com.psugv.capstone.login.controller;


import com.psugv.capstone.exception.InsertErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.psugv.capstone.login.service.ILoginService;

import java.util.Map;

@Controller
@Component
public class LoginController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private ILoginService loginService;

    @GetMapping(path="/")
    public String mainPagePath() {

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
    @PostMapping(path="/register", consumes = "application/json")
    /**
     * Key: username, value: input username.
     * Key: password, value: input password.
     * Key: name, value: name displayed in the chat.
     * Key: gender, value: drop down list, should only have have male, female, and other.
     */
    public @ResponseBody String registerToApp(Map<String, String> inputMap){

        boolean result;

        try {
            result = loginService.registration(inputMap);

        }catch (InsertErrorException e){

            LOGGER.error(e.getMessage());
            return Boolean.FALSE.toString();
        }
        return String.valueOf(result);
    }
}