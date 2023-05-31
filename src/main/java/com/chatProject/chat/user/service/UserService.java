package com.chatProject.chat.user.service;

import com.chatProject.chat.common.Utils.Utils;
import com.chatProject.chat.common.dto.CommonDto;
import com.chatProject.chat.common.users.LoginUserManager;
import com.chatProject.chat.user.dto.UserDto;
import com.chatProject.chat.user.mapper.UserMapper;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Map;


@Slf4j
@Service
public class UserService{
    @Autowired
    private UserMapper userMapper;

    public Integer login(UserDto.userLoginReq req, HttpServletRequest request) throws NoSuchAlgorithmException {
        // 사용자 조회 (없으면 존재하지않는 ID)
        UserDto.userLoginCheck loginCheck = userMapper.userCheck(req);
        if (ObjectUtils.isEmpty(loginCheck)){
            return 0;
        }
        // 로그인 중 비밀번호 매칭확인
        Map<String, String> alg = Utils.makeHexByPwAndSalt(req.getUserPw(), loginCheck.getSalt());
        if (!alg.get("HEX").equals(loginCheck.getUserPw())){
            // 비밀번호 상이
            return 0;
        }else {
            // 비밀번호 매칭
            UserDto.userInfo userInfo = userMapper.findUserByUserIdx(loginCheck.getUserIdx());

            HttpSession session = request.getSession();
            session.setAttribute("userInfo", userInfo);

            LoginUserManager.addUser(userInfo);

            return 1;
        }
    }

    public Integer userIdCheck(UserDto.userIdCheckReq req) {
        Integer result;
        result = userMapper.userIdCheck(req);
        if (result == 0) return 0;
        return result;
    }

    public CommonDto.commentRes userRegister(UserDto.userRegisterReq req) throws NoSuchAlgorithmException {
        // ID 중복 교차 확인
        if (userMapper.userIdCheck(new UserDto.userIdCheckReq(req.getUserId())) == 1){
            return new CommonDto.commentRes("ID 중복", "이미 존재하는 ID입니다");
        }

        // PW 값 상이한지 확인
        if (!req.getUserPw().equals(req.getUserPwTest())){
            return new CommonDto.commentRes("PW 상이", "비밀번호가 같지않습니다.");
        }

        // SALT 생성 및 PW SHA256 암호화
        Map<String, String> alg = Utils.makeSaltAndHexByPw(req.getUserPw());
        req.setSaltAndHex(alg.get("SALT"), alg.get("HEX"));

        Integer userRegister = userMapper.userRegister(req);

        if (userRegister == 1){
            return new CommonDto.commentRes("등록완료", "회원가입이 완료되었습니다.");
        }else {
            return new CommonDto.commentRes("에러", "회원가입중 에러가발생하였습니다.");
        }

    }
}
