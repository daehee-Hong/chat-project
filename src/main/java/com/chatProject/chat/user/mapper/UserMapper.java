package com.chatProject.chat.user.mapper;

import com.chatProject.chat.user.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
@Repository
@Mapper
public interface UserMapper {
    String userCheck(UserDto.userLoginReq req);
}
