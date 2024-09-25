package com.psugv.capstone.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {

    @GetMapping(path="/signup")
    public String loginPagePath() {
        return "/signup";
    }


}
