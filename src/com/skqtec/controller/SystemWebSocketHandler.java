package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.MessageEntity;
import com.skqtec.repository.MessageRepository;
import com.skqtec.tools.RedisAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
@Controller
public class SystemWebSocketHandler implements WebSocketHandler {
    @Autowired
    private MessageRepository messageRepository;
    public static Map<String,WebSocketSession> sessions = new HashMap<String, WebSocketSession>();
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("ConnectionEstablished");
        //String i = session.getId();
        //System.out.println(i + " user size:" + users.size());

    }
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> msg) throws Exception {

        String chatMessage=(String) msg.getPayload();
        System.out.println(chatMessage);
        JSONObject j=JSON.parseObject(chatMessage);
        String messageFrom=(String)j.get("messageFrom");
        String messageTo=(String)j.get("messageTo");
        String messageContent=(String)j.get("messageContent");
        String contentType=(String)j.get("contentType");
        String headOwner=(String)j.get("headOwner");
        if(messageContent.equals("connect")&&contentType.equals("-1")){
            sessions.put(messageFrom,session);
        }else {
            try {
                //存入redis
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("messageFrom", messageFrom);
                jsonObject.put("messageContent", messageContent);
                jsonObject.put("contentType", contentType);
                jsonObject.put("headOwner",headOwner);
                jsonObject.put("createTime", new Date());
                JedisPool pool = RedisAPI.getPool();
                Jedis jedis = pool.getResource();
                jedis.lpush(messageTo, jsonObject.toString());
                pool.returnResource(jedis);
                //存入数据库
                MessageEntity message = new MessageEntity();
                String uuid = UUID.randomUUID().toString().replace("-", "");
                message.setId(uuid);
                message.setContent(messageContent);
                message.setTime(new Timestamp(new Date().getTime()));
                message.setContentType(Integer.parseInt(contentType));
                message.setFromUserId(messageFrom);
                message.setToUserId(messageTo);
                messageRepository.save(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
        if (session.isOpen()) {
            session.close();
            sessions.remove(session);
        }

    }
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        System.out.println("ConnectionClosed");
        sessions.remove(session);
    }
    public boolean supportsPartialMessages() {
        return false;
    }
}
