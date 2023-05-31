package com.chatProject.chat.common.users;

import com.chatProject.chat.chat.dto.ChatRoomDto;
import com.chatProject.chat.user.dto.UserDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginUserManager {
    /**
     * @see 로그인 사용자 리스트 모듈
     * (Map<사용자IDX, 사용자정보DTO>)
     * */
    private static Map<Long, UserDto.userInfo> loginUsers = new ConcurrentHashMap<>();

    public static void addUser(UserDto.userInfo user) {
        loginUsers.put(user.getUserIdx(), user);
    }

    public static void removeUser(Long userIdx) {
        loginUsers.remove(userIdx);
    }

    public static UserDto.userInfo getUser(Long userIdx) {
        return loginUsers.get(userIdx);
    }

    public static List<UserDto.userInfo> getAllLoggedUsers() {
        return new ArrayList<>(loginUsers.values());
    }
}
