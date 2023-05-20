package com.chatProject.chat.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/login")
    private String loginPage() {
        return "/login/login";
    }

    @GetMapping("/register")
    private String registerPage() {
        return "/login/register";
    }
}
