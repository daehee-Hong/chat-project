package com.chatProject.chat.user.service;

import com.chatProject.chat.user.dto.UserDto;
import com.chatProject.chat.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    public Integer login(UserDto.userLoginReq req) {
        Integer result = 0;
        String userId = req.getUserId();
        String userPw = req.getUserPw();

        String userSalt = userMapper.userCheck(req);
        System.out.println("userSalt = " + userSalt);
        if ("".equals(userSalt) || userSalt == null){
            return -2;
        }
        return 1;
    }
}
