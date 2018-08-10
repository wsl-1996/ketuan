package com.skqtec.timertask;

import com.skqtec.controller.SystemWebSocketHandler;
import com.skqtec.tools.RedisAPI;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Map;

//发送redis中的消息给客户端
@Component
class SendMessage{
    @Scheduled(cron = "0 * * * * ? ")
    public void run(){
        Map<String,WebSocketSession> sessions=SystemWebSocketHandler.sessions;
        JedisPool pool=RedisAPI.getPool();
        Jedis jedis=pool.getResource();
        for (Map.Entry<String, WebSocketSession>entry: sessions.entrySet()){
            String messageFrom=entry.getKey();
            WebSocketSession session=entry.getValue();
            try {
                while (jedis.exists(messageFrom) && jedis.llen(messageFrom) > 0) {
                    TextMessage textMessage = new TextMessage(jedis.lpop(messageFrom), true);
                    session.sendMessage(textMessage);
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        pool.returnResource(jedis);
    }
}