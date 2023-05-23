package com.chatProject.chat.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

public class UserDto {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userInfo {
        private Long userIdx;
        private String userId;
        private String nickName;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userLoginReq {
        @NotBlank
        private String userId;
        @NotBlank
        private String userPw;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userLoginCheck {
        private Long userIdx;
        private String salt;
        private String userPw;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userIdCheckReq {
        @NotBlank
        private String userId;
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class userRegisterReq {
        @NotBlank
        private String userId;
        @NotBlank
        private String userPw;
        @NotBlank
        private String userPwTest;
        @NotBlank
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
