package com.chatProject.chat.user.service;

import com.chatProject.chat.common.Utils.Utils;
import com.chatProject.chat.user.dto.UserDto;
import com.chatProject.chat.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Map;


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

    public Integer userIdCheck(UserDto.userIdCheckReq req) {
        Integer result;
        result = userMapper.userIdCheck(req);
        if (result == 0) return 0;
        return result;
    }

    public UserDto.userRegisterRes userRegister(UserDto.userRegisterReq req) throws NoSuchAlgorithmException {
        // ID 중복 교차 확인
        if (userMapper.userIdCheck(new UserDto.userIdCheckReq(req.getUserId())) == 1){
            return new UserDto.userRegisterRes("ID 중복", "이미 존재하는 ID입니다");
        }

        // PW 값 상이한지 확인
        if (!req.getUserPw().equals(req.getUserPwTest())){
            return new UserDto.userRegisterRes("PW 상이", "비밀번호가 같지않습니다.");
        }

        // SALT 생성 및 PW SHA256 암호화
        Map<String, String> alg = Utils.makeSecurityByPw(req.getUserPw());
        req.setSaltAndHex(alg.get("SALT"), alg.get("HEX"));

        Integer userRegister = userMapper.userRegister(req);

        if (userRegister == 1){
            return new UserDto.userRegisterRes("등록완료", "회원가입이 완료되었습니다.");
        }else {
            return new UserDto.userRegisterRes("에러", "회원가입중 에러가발생하였습니다.");
        }

    }
}
