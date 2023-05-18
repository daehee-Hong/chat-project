package com.chatProject.chat.websocket;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpointConfig;

public class WebSocketConfigurator extends ServerEndpointConfig.Configurator {

    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) { // 통신 규칙 정하기
        HttpSession session = (HttpSession)request.getHttpSession(); // 1) 세션 정보를 꺼내라
        sec.getUserProperties().put("hSession", session); // 2) 엔드포인트 전달하고 싶은 사용자 정보를 담음 ServerEndpointConfig는 엔드포인트에 접근 가능하다.
    }
}
