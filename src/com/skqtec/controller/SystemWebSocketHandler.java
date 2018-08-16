package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;

@Controller
public class SystemWebSocketHandler implements WebSocketHandler {
    @Autowired
    private MessageRepository messageRepository;
    //private String sessionKey;
    public static HashMap<String,WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
    private static HashMap<String,String>sessionKey=new HashMap<String, String>();
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("ConnectionEstablished"+session.getId());
       // System.out.println(i + " user size:" + users.size());

    }
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> msg) throws Exception {

        String chatMessage=(String) msg.getPayload();
        System.out.println(chatMessage);
        JSONObject j=JSON.parseObject(chatMessage);
        String messageFrom=(String)j.get("messageFrom");
       // String messageTo=(String)j.get("messageTo");
        String messageContent=(String)j.get("messageContent");
        String contentType=(String)j.get("contentType");
        //String headOwner=(String)j.get("headOwner");
        if(messageContent.equals("connect")&&contentType.equals("-1")){
            sessionKey.put(session.getId(),messageFrom);
            sessions.put(messageFrom,session);
            return;
        }
    }
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
        String key=sessionKey.get(session.getId());
        sessions.remove(key);
    }
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("ConnectionClosed");
        String key=sessionKey.get(session.getId());
        sessions.remove(key);
        System.out.println("已移除"+key);
    }
    public boolean supportsPartialMessages() {
        return false;
    }
}
