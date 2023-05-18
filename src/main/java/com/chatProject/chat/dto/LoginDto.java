package com.chatProject.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class LoginDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class loginReq {
        private String userId;
        private String userPw;
    }
}
