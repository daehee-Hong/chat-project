package com.chatProject.chat.user.controller;

import com.chatProject.chat.user.dto.UserDto;

import com.chatProject.chat.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserRestController {

    private final UserService userService;

    /**
     * @author daehee
     * @param UserDto.userLoginReq 로그인 정보
     * @return Integer
     * @see -1(로그인 중 에러), 0(로그인 실패), 1(로그인 성공)
     * @// TODO: 2023-05-20 로그인 기능 미완
     * */
    @PostMapping("/login")
    private ResponseEntity login(
             @RequestBody UserDto.userLoginReq req
        ) {
        Integer result;
        try{
            result = userService.login(req);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Login Error : " + e.getMessage());
            result = -1;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * @author daehee
     * @param String userId (확인할 사용자 ID)
     * @return Integer
     * @see -1(아이디 확인 중 에러), 0(아이디 확인 실패), 1(아이디 확인 성공)
     * */
    @PostMapping("/userIdCheck")
    private ResponseEntity userIdCheck(
            @RequestBody UserDto.userIdCheckReq req
        ){
        Integer result;
        try{
            result = userService.userIdCheck(req);
        }catch (Exception e){
            e.printStackTrace();
            log.error("idCheck Error : " + e.getMessage());
            result = -1;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    /**
     * @author daehee
     * @param String userId (등록할 사용자 ID)
     * @param String nickName (등록할 사용자 닉네임)
     * @param String userPw (등록할 사용자 PW)
     * @param String userPwTest (등록할 사용자 PW)
     * @return Integer
     * @see -1(아이디 확인 중 에러), 0(아이디 확인 실패), 1(아이디 확인 성공)
     * */
    @PostMapping("/user-register")
    private ResponseEntity userRegister(
            @RequestBody UserDto.userRegisterReq req
    ){
        UserDto.userRegisterRes result = null;
        try{
            result = userService.userRegister(req);
        }catch (Exception e){
            e.printStackTrace();
            log.error("idCheck Error : " + e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }}