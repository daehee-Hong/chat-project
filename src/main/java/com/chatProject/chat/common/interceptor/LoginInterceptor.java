package com.chatProject.chat.common.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        String requestURI = request.getRequestURI();
        log.info("접속 URL : " + requestURI);
        HttpSession session = request.getSession(false);

        if(session == null || session.getAttribute("userInfo") == null) {
            log.warn("로그인되지 않은 사용자 : ");
            response.sendRedirect("/user/login");
            return false;
        }
        // 로그인
        return true;
    }
}
