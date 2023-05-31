package com.chatProject.chat.chat.mapper;


import com.chatProject.chat.chat.dto.ChatRoomDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatMapper {
    List<ChatRoomDto.chatRoomResult> selectChatRoom(ChatRoomDto.chatRoomReq req);

    Integer selectChatRoomTotal(ChatRoomDto.chatRoomReq req);

    Integer insertChatRoom(ChatRoomDto.chatRoomRegisterReq req);

    Integer insertChatRoomMapping(ChatRoomDto.chatRoomRegisterReq req);

    ChatRoomDto.chatRoomPwCheck chatRoomCheck(ChatRoomDto.chatRoomPwCheckReq req);

    Integer insertChatUserMapping(ChatRoomDto.chatRoomPwCheckReq req);

    Integer isUserInCheckRoom(ChatRoomDto.chatRoomPwCheckReq req);

    ChatRoomDto.chatRoomPageRes selectChatRoomPageDetail(Long chatRoomIdx);

    List<ChatRoomDto.chatRoomUser> selectChatRoomUserList(ChatRoomDto.chatRoomPwCheckReq req);
}
