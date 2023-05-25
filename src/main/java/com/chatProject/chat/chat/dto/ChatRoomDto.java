package com.chatProject.chat.chat.dto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.ref.PhantomReference;
import java.util.List;

public class ChatRoomDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chatRoomReq {
        private Long userIdx;
        private Integer draw;
        private Integer start;
        private Integer length;
        private Integer searchType;
        private String searchText;

        public void setUserIdx(Long userIdx){
            this.userIdx = userIdx;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chatRoomResult {
        private Long chatRoomIdx;
        private String chatRoomTitle;
        private String chatRoomIntroduce;
        private String chatRoomRegdate;
        private String chatRoomStatus;
        private Integer chatUserCount;
        private String userId;
        private String nickName;
        private Integer isUserIn;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chatRoomRes {
        private Integer draw;
        private Integer start;
        private Integer length;
        private Integer recordsTotal;
        private Integer recordsFiltered;
        private List<chatRoomResult> list;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chatRoomRegisterReq {
        private Long chatRoomIdx;
        private Long userIdx;
        @NotBlank
        private String chatRoomTitle;
        private String chatRoomIntroduce;
        @NotNull
        private Integer chatRoomStatus;
        private String chatRoomPw;
        private String chatRoomPwTest;
        private String salt;
        private String hex;

        public void setUserIdx(Long userIdx){
            this.userIdx = userIdx;
        }

        public void setSaltAndHex(String salt, String hex){
            this.salt = salt;
            this.hex = hex;
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chatRoomPwCheckReq {
        private Long userIdx;
        private Long chatRoomIdx;
        private String chatRoomPw;

        public void setUserIdx(Long userIdx){
            this.userIdx = userIdx;
        }
    }
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chatRoomPwCheck {
        private Long chatRoomIdx;
        private String chatRoomStatus;
        private String chatRoomPw;
        private String salt;
    }
}
