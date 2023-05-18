package com.chatProject.chat.controller;

import com.chatProject.chat.dto.LoginDto;
import com.chatProject.chat.service.LoginService;
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
public class LoginRestController {

    private final LoginService loginService;

    @PostMapping
    private ResponseEntity login(
            @RequestBody LoginDto.loginReq req
            ) {
        Integer result = 0;
        try{
            result = loginService.login(req);
        }catch (Exception e){
            e.printStackTrace();
            log.error("Login Error");
            result = -1;
        }finally {
            return new ResponseEntity(result, HttpStatus.OK);
        }
    }
}
