package com.chatProject.chat.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/")
    private String loginPage() {
        return "/login/login";
    }

    @GetMapping("/register")
    private String registerPage() {
        return "/login/register";
    }
}
