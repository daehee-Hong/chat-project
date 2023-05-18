package com.chatProject.chat.websocket;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint(value = "/chat", configurator = WebSocketConfigurator.class)
public class WebSocketServer {
    private static Set<Session> sessions = Collections.synchronizedSet(new HashSet<>());
    private HttpSession hSession;
    @OnOpen
    public void onOpen(Session session, EndpointConfig config) {
        // 클라이언트 연결시
        this.hSession = (HttpSession) config.getUserProperties().get("hSession"); // WebSocketConfigurator 에서 넣은 세션 가져옴
        sessions.add(session);
        System.out.println(hSession.getAttribute("loginID")); // 세션 안의 키를 통해 값을 꺼낸다.
    }

    @OnMessage
    public void onMessage(String message, Session session) throws IOException {
        // 클라이언트에게 메시지를 전달받을 시
        System.out.println("Received message from client: " + session.getId() + ", Message: " + message);

        for (Session s : sessions) {
            s.getBasicRemote().sendText(message);
        }
    }
    @OnClose
    public void onClose(Session session) {
        // 클라이언트가 연결을 종료할 시
        sessions.remove(session);
        System.out.println("Client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable error) {
        System.out.println("Error occurred for client: " + session.getId() + ", Error: " + error.getMessage());
    }

}
