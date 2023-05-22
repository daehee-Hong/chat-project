package com.chatProject.chat.chat.service;

import com.chatProject.chat.chat.dto.ChatDto;
import com.chatProject.chat.chat.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatMapper chatMapper;

    public void selectChatRoom(ChatDto.chatRoomReq req) {
        chatMapper.selectChatRoom(req);
    }
}
