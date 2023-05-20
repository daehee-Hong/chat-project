package com.chatProject.chat.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

public class UserDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userLoginReq {
        private String userId;
        private String userPw;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userIdCheckReq {
        private String userId;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userRegisterReq {
        private String userId;
        private String userPw;
        private String userPwTest;
        private String nickName;
        private String salt;
        private String hex;

        public void setSaltAndHex(String salt, String hex){
            this.salt = salt;
            this.hex = hex;
        }
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userRegisterRes {
        private String title;
        private String comment;
    }
}
