package com.chatProject.chat.chat.controller;

import com.chatProject.chat.chat.dto.ChatDto;
import com.chatProject.chat.chat.service.ChatService;
import com.chatProject.chat.user.dto.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@RestController
public class ChatRestController {

    private final ChatService chatService;

    /**
     * @author daehee
     * @param String searchText (사용자 ID)
     * @param String pa (사용자 PW)
     * @return Integer
     * @see -1(로그인 중 에러), 0(로그인 실패), 1(로그인 성공)
     * */
    @PostMapping("/chat-room")
    private ResponseEntity selectChatRoom(
            @RequestBody ChatDto.chatRoomReq req,
            HttpSession session
    ) {
        try{
            UserDto.userInfo userInfo = (UserDto.userInfo) session.getAttribute("userInfo");
            req.setUserIdx(userInfo.getUserIdx());

            ChatDto.chatRoomRes chatRoomRes = chatService.selectChatRoom(req);
            return new ResponseEntity<>(chatRoomRes, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            log.error("selectChatRoom Error : " + e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
