package com.chatProject.chat.chat.service;

import com.chatProject.chat.chat.dto.ChatDto;
import com.chatProject.chat.chat.mapper.ChatMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatMapper chatMapper;

    public ChatDto.chatRoomRes selectChatRoom(ChatDto.chatRoomReq req) {
        Integer total = chatMapper.selectChatRoomTotal(req);// 총 건수
        return new ChatDto.chatRoomRes(
                req.getDraw(),
                req.getStart(),
                req.getLength(),
                total,
                total,
                chatMapper.selectChatRoom(req));        // 리스트
    }
}
