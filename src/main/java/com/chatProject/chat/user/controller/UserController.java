package com.chatProject.chat.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

    @GetMapping("/login")
    private String loginPage(
            HttpServletRequest request
    ) {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return "/login/login";
    }

    @GetMapping("/register")
    private String registerPage() {
        return "/login/register";
    }
}
