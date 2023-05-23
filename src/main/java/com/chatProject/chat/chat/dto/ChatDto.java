package com.chatProject.chat.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.lang.ref.PhantomReference;
import java.util.List;

public class ChatDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class chatRoomReq {
        private Integer draw;
        private Integer recordsTotal;
        private Integer recordsFiltered;
        private Integer start;
        private Integer length;
        private Integer searchType;
        private String searchText;
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
}
