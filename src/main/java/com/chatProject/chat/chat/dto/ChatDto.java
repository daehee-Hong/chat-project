package com.chatProject.chat.chat.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
        private Integer chatRoomIdx;
        private Integer chatRoomTitle;
        private Integer chatRoomIntroduce;
        private Integer chatRoomRegdate;
        private Integer chatRoomStatus;
        private Integer chatRoomCount;
    }
}
