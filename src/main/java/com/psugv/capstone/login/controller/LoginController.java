package com.psugv.capstone.login.controller;


import com.psugv.capstone.exception.InsertErrorException;
import com.psugv.capstone.login.service.ILoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URI;
import java.util.Map;

@Controller
@Component
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
        return "/open/login";
    }

    @GetMapping(path = "/index")
    public String toIndexPage() {
        return "/open/index";
    }

    @GetMapping(path = "/error")
    public String toErrorPage() {
        return "/open/error";
    }

    @GetMapping(path = "/signup")
    public String toSignupPage() {
        return "/open/signup";
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
    @PostMapping(path = "/register", consumes = "application/json")
    /**
     * Key: username, value: input username.
     * Key: password, value: input password.
     * Key: name, value: name displayed in the chat.
     * Key: gender, value: drop down list, should only have have male, female, and other.
     */
    public @ResponseBody String registerToApp(@RequestBody Map<String, String> inputMap) {

        LOGGER.info("registerToApp() called");
        LOGGER.debug(inputMap.toString());
        boolean result = false;

        URI redirectUri;

        try {
            result = loginService.registration(inputMap);

        } catch (InsertErrorException e) {

            LOGGER.error(e.getMessage());
            return String.valueOf(result);
        }
        if(!result) {

            LOGGER.debug("Registration failed");
            return String.valueOf(result);
        }

        LOGGER.info("register succeed^_^");
        return String.valueOf(result);
    }
}