package com.chatProject.chat.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/chat")
@Controller
public class ChatController {

    @GetMapping("/main")
    private String chatMainPage() {
        return "/chat/mainChat";
    }

    @GetMapping("/register")
    private String chatRegisterPage() {
        return "/chat/chatRegister";
    }
}
