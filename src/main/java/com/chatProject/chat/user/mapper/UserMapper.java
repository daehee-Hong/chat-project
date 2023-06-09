package com.chatProject.chat.user.mapper;

import com.chatProject.chat.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface UserMapper {
    UserDto.userLoginCheck userCheck(UserDto.userLoginReq req);

    Integer userIdCheck(UserDto.userIdCheckReq req);

    Integer userRegister(UserDto.userRegisterReq req);

    UserDto.userInfo findUserByUserIdx(Long userIdx);
}
