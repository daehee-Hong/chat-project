package com.chatProject.chat.chat.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/chat-room/{id}")
    private String chatPage(
            @PathVariable("id") Integer chatRoomIdx,
            HttpSession session
    ) {

        return "/chat/chatPage";
    }
}
