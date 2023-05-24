package com.chatProject.chat.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class CommonDto {
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class commentRes {
        private String title;
        private String comment;
    }
}
