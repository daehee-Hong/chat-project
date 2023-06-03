package com.chatProject.chat.chat.service;

import com.chatProject.chat.chat.dto.ChatRoomDto;
import com.chatProject.chat.chat.mapper.ChatMapper;
import com.chatProject.chat.common.Utils.Utils;
import com.chatProject.chat.common.dto.CommonDto;
import com.chatProject.chat.common.users.LoginUserManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatMapper chatMapper;

    public ChatRoomDto.chatRoomRes selectChatRoom(ChatRoomDto.chatRoomReq req) {
        Integer total = chatMapper.selectChatRoomTotal(req); // 총 건수
        return new ChatRoomDto.chatRoomRes(
                req.getDraw(),
                req.getStart(),
                req.getLength(),
                total,
                total,
                chatMapper.selectChatRoom(req));        // 리스트
    }
    @Transactional
    public CommonDto.commentRes insertChatRoom(ChatRoomDto.chatRoomRegisterReq req) throws NoSuchAlgorithmException {
        // 유효성검사
        if (req.getChatRoomStatus() == 0){
            if ("".equals(req.getChatRoomPw()) || "".equals(req.getChatRoomPwTest())){
                return new CommonDto.commentRes("유효성검사", "비밀번호를 전부 입력해주세요.");
            }
            if (!req.getChatRoomPw().equals(req.getChatRoomPwTest())){
                return new CommonDto.commentRes("유효성검사", "비밀번호가 같지않습니다.");
            }

            Map<String, String> alg = Utils.makeSaltAndHexByPw(req.getChatRoomPw());
            req.setSaltAndHex(alg.get("SALT"), alg.get("HEX"));
        }
        // 등록
        Integer chatRegister = 0;
        chatRegister += chatMapper.insertChatRoom(req);
        chatRegister += chatMapper.insertChatRoomMapping(req);

        if (chatRegister == 2){
            return new CommonDto.commentRes("등록완료", "채팅방이 등록되었습니다.");
        }else {
            return new CommonDto.commentRes("서버에러", "잠시후 다시 시도해주세요.");
        }
    }

    public CommonDto.commentRes chatRoomPwCheck(ChatRoomDto.chatRoomPwCheckReq req) throws NoSuchAlgorithmException {
        // 채팅방 존재 확인
        ChatRoomDto.chatRoomPwCheck chatRoomCheck = chatMapper.chatRoomCheck(req);
        if (ObjectUtils.isEmpty(chatRoomCheck)){
            return new CommonDto.commentRes("채팅방이 없습니다.", "새로고침을 해주세요.");
        }
        // 비공개여부확인
        if (!"비공개".equals(chatRoomCheck.getChatRoomStatus())){
            return new CommonDto.commentRes("비공개 채팅방이 아닙니다.", "다시시도 해주세요.");
        }
        // 비밀번호 매칭확인
        Map<String, String> alg = Utils.makeHexByPwAndSalt(req.getChatRoomPw(), chatRoomCheck.getSalt());
        if (!alg.get("HEX").equals(chatRoomCheck.getChatRoomPw())){
            // 비밀번호 상이
            return new CommonDto.commentRes("유효성검사", "비밀번호가 다릅니다.");
        }else {
            // 비밀번호 매칭
            Integer check = chatMapper.isUserInCheckRoom(req);
            if (check == 0){
                chatMapper.insertChatUserMapping(req);
            }

            return new CommonDto.commentRes("접속완료", "");
        }
    }

    public Integer chatRoomPageCheck(ChatRoomDto.chatRoomPwCheckReq req) {
        // 비공개 방인지 확인
        ChatRoomDto.chatRoomPwCheck chatRoomCheck = chatMapper.chatRoomCheck(req);
        if (ObjectUtils.isEmpty(chatRoomCheck)){
            return -1;
        }
        // 비공개여부확인
        Integer check = chatMapper.isUserInCheckRoom(req);
        if ("비공개".equals(chatRoomCheck.getChatRoomStatus())){
            if (check == 0){
                return -1;
            }else {
                return 1;
            }
        }else {
            if (check == 0){
                chatMapper.insertChatUserMapping(req);
            }
            return 1;
        }

    }

    public ChatRoomDto.chatRoomPageRes selectChatRoomPageDetail(Long chatRoomIdx) {
        return chatMapper.selectChatRoomPageDetail(chatRoomIdx);
    }

    public List<ChatRoomDto.chatRoomUser> chatRoomPageSelectUserList(ChatRoomDto.chatRoomPwCheckReq req) {
        // 채팅방에 있는 사용자인지 확인
        Integer check = chatMapper.isUserInCheckRoom(req);
        if (check == 1){
            return chatMapper.selectChatRoomUserList(req).stream()
                    .peek(v -> v.setUserLoginStatus(LoginUserManager.getAllLoggedUsers()))
                    .collect(Collectors.toList());
        }else {
            return new ArrayList<>();
        }
    }

    public List<ChatRoomDto.chatRoomMessage> chatRoomPageSelectChatMessageList(ChatRoomDto.chatRoomPwCheckReq req) {
        // 채팅방에 있는 사용자인지 확인
        Integer check = chatMapper.isUserInCheckRoom(req);
        if (check == 1){
            return chatMapper.selectChatMassageList(req);
        }else {
            return new ArrayList<>();
        }
    }

    public Object chatRoomPageSendChatMessage(ChatRoomDto.chatRoomPwCheckReq req) {
        // 채팅방에 있는 사용자인지 확인
        Integer check = chatMapper.isUserInCheckRoom(req);
        if (check == 1){
            return 1;
        }else {
            return 2;
        }
    }
}
