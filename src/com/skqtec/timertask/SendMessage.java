package com.skqtec.timertask;

import com.skqtec.controller.SystemWebSocketHandler;
import com.skqtec.tools.RedisAPI;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Date;
import java.util.HashMap;

//发送redis中的消息给客户端
@Component
class SendMessage{
    @Scheduled(cron = "1/5 * * * * ?")
    public void sendMessage(){
        HashMap<String,WebSocketSession> sessions=SystemWebSocketHandler.sessions;
        //System.out.println((new Date())+"start111");
       JedisPool pool=RedisAPI.getPool();
       Jedis jedis=pool.getResource();
       for (HashMap.Entry<String, WebSocketSession>entry: sessions.entrySet()){
           String messageFrom=entry.getKey();
           System.out.println(new Date()+"接受者ID"+messageFrom);
           WebSocketSession session=entry.getValue();
           try {
               while (jedis.exists(messageFrom) && jedis.llen(messageFrom) > 0) {
                   if(session.isOpen()) {
                       TextMessage textMessage = new TextMessage(jedis.lpop(messageFrom), true);
                       synchronized (session) {
                           session.sendMessage(textMessage);
                           System.out.println("**sendSuccess:"+textMessage.toString());
                       }
                   }else{
                       System.out.println("connectover");
                       break;
                   }
               }
            }catch(Exception e){
              e.printStackTrace();
          }
        }
        //System.out.println((new Date())+"end");
        pool.returnResource(jedis);
    }
}