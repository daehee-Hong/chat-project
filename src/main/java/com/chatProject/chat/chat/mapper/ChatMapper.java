package com.chatProject.chat.chat.mapper;

import com.chatProject.chat.chat.dto.ChatDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<ChatDto.chatRoomResult> selectChatRoom(ChatDto.chatRoomReq req);

    Integer selectChatRoomTotal(ChatDto.chatRoomReq req);
}
