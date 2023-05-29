package com.chatProject.chat.chat.controller;

import com.chatProject.chat.chat.dto.ChatRoomDto;
import com.chatProject.chat.chat.service.ChatService;
import com.chatProject.chat.user.dto.UserDto;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@RequestMapping("/chat")
@RequiredArgsConstructor
@Controller
public class ChatController {

    private final ChatService chatService;

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
            @PathVariable("id") Long chatRoomIdx,
            HttpSession session
    ) {
        UserDto.userInfo userInfo = (UserDto.userInfo) session.getAttribute("userInfo");

        Integer result = chatService.chatRoomPageCheck(new ChatRoomDto.chatRoomPwCheckReq(chatRoomIdx, userInfo.getUserIdx(), ""));
        if (result == 1){
            return "/chat/chatPage";
        }else {
            return "/error/error";
        }
    }
}
