package com.chatProject.chat.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userLoginReq {
        private String userId;
        private String userPw;
    }
}
