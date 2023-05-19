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

    @PostMapping("/login")
    private ResponseEntity login(
             @RequestBody UserDto.userLoginReq req
            ) {
        Integer result = 0;
        try{
            result = userService.login(req);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Login Error : " + e.getMessage());
            result = -1;
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
