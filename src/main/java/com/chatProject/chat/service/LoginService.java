package com.chatProject.chat.service;

import com.chatProject.chat.dto.LoginDto;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    public Integer login(LoginDto.loginReq req) {
        String userId = req.getUserId();
        String userPw = req.getUserPw();
        return 1;
    }
}
