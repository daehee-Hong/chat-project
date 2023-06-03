package com.chatProject.chat.chat.controller;

import com.chatProject.chat.common.users.LoginUserManager;
import com.chatProject.chat.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Objects;

@Controller
public class ChatMessageController {

    @MessageMapping("chat/{id}")
    @SendTo("/topic/message/{id}")
    public String chat(
            String message
            ){
        System.out.println("Asdasd : " + message);
        return message;
    }

}
