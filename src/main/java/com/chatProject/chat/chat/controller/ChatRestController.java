package com.chatProject.chat.chat.controller;


import com.chatProject.chat.chat.dto.ChatRoomDto;
import com.chatProject.chat.chat.service.ChatService;
import com.chatProject.chat.common.dto.CommonDto;
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
import java.util.Enumeration;
import java.util.Map;

@Slf4j
@RequestMapping("/api/v1/chat")
@RequiredArgsConstructor
@RestController
public class ChatRestController {

    private final ChatService chatService;

    /**
     * @author daehee
     * @param Long userIdx (사용자 IDX)
     * @param Integer draw (현재 페이지)
     * @param Integer start (조회 시작값)
     * @param Integer length (조회 종료값)
     * @param Integer searchType (검색 조건 (1=제목,2=작성자))
     * @param String searchText (검색내용)
     * @return List<chatRoomRes>
     * @see 채팅방 리스트 조회
     * */
    @PostMapping("/chat-room")
    private ResponseEntity selectChatRoom(
            @RequestBody ChatRoomDto.chatRoomReq req,
            HttpSession session
    ) {
        try{

            UserDto.userInfo userInfo = (UserDto.userInfo) session.getAttribute("userInfo");
            req.setUserIdx(userInfo.getUserIdx());

            ChatRoomDto.chatRoomRes chatRoomRes = chatService.selectChatRoom(req);
            return new ResponseEntity<>(chatRoomRes, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            log.error("selectChatRoom Error : " + e.getMessage());
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author daehee
     * @param Long chatRoomIdx (채팅방 등록 후 IDX)
     * @param Long userIdx (사용자 IDX)
     * @param String chatRoomTitle (채팅방 제목)
     * @param String chatRoomIntroduce (채팅방 소개)
     * @param Integer chatRoomStatus (채팅방 공개여부 (1=공개,0=미공개))
     * @param String chatRoomPw (채팅방 비밀번호)
     * @param String chatRoomPwTest (채팅방 비밀번호 확인)
     * @param String salt (채팅방 비밀번호 SALT값)
     * @param String hex (채팅방 비밀번호 암호화값)
     * @return CommonDto.commentRes
     * @see 채팅방 등록
     * */
    @PostMapping("/chat-room-register")
    private ResponseEntity insertChatRoom(
            @Valid @RequestBody ChatRoomDto.chatRoomRegisterReq req,
            HttpSession session
    ) {
        CommonDto.commentRes result;
        try{
            UserDto.userInfo userInfo = (UserDto.userInfo) session.getAttribute("userInfo");
            req.setUserIdx(userInfo.getUserIdx());

            result = chatService.insertChatRoom(req);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            log.error("selectChatRoom Error : " + e.getMessage());
            return new ResponseEntity<>(new CommonDto.commentRes("서버에러", "잠시후 다시 시도해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author daehee
     * @param Long chatRoomIdx (채팅방 IDX)
     * @param Long userIdx (사용자 IDX)
     * @param String chatRoomPw (채팅방 비밀번호)
     * @return CommonDto.commentRes
     * @see 채팅방 비밀번호 체크
     * */
    @PostMapping("/chat-room-pw-check")
    private ResponseEntity chatRoomPwCheck(
            @Valid @RequestBody ChatRoomDto.chatRoomPwCheckReq req,
            HttpSession session
    ) {
        CommonDto.commentRes result;
        try{
            UserDto.userInfo userInfo = (UserDto.userInfo) session.getAttribute("userInfo");
            req.setUserIdx(userInfo.getUserIdx());

            result = chatService.chatRoomPwCheck(req);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            log.error("selectChatRoom Error : " + e.getMessage());
            return new ResponseEntity<>(new CommonDto.commentRes("서버에러", "잠시후 다시 시도해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @author daehee
     * @param Long chatRoomIdx (채팅방 IDX)
     * @param Long userIdx (사용자 IDX)
     * @return CommonDto.commentRes
     * @see 채팅방 사용자 리스트 조회
     * */
    @GetMapping("/chat-room-users/{id}")
    private ResponseEntity chatRoomPageSelectUserList(
            @PathVariable("id") Long chatRoomIdx,
            HttpSession session
    ) {
        try{
            UserDto.userInfo userInfo = (UserDto.userInfo) session.getAttribute("userInfo");

            return new ResponseEntity<>(chatService.chatRoomPageSelectUserList(new ChatRoomDto.chatRoomPwCheckReq(userInfo.getUserIdx(), chatRoomIdx, "")), HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            log.error("selectChatRoom Error : " + e.getMessage());
            return new ResponseEntity<>(new CommonDto.commentRes("서버에러", "잠시후 다시 시도해주세요."), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
