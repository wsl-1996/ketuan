/*package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.MessageEntity;
import com.skqtec.repository.MessageRepository;
import com.skqtec.tools.RedisAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;
import java.util.logging.Logger;
import org.springframework.web.socket.*;
@Controller
@ServerEndpoint("/websocket")
public class Websockets {
    static Logger logger = Logger.getLogger(Websockets.class.getName());
    private String socketId;
    private Session session;
    @Autowired
    private MessageRepository messageRepository;
    //@Autowired
    //private WebSocketsRepository webSocketsRepository;
    //private static int onlineCount = 0;
    //public static List<Websockets> webSocketSet = new ArrayList<Websockets>();
    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        //webSocketsRepository.addWebSocket(this);
        //webSocketSet.add(this);     //加入set中
        //addOnlineCount();           //在线数加1
        //System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());
    }
    @OnClose
    public void onClose(Session session){
        //webSocketsRepository.deleteWebSockets(this);
        //webSocketSet.remove(this);  //从set中删除
        //subOnlineCount();           //在线数减1
        //System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
    @OnMessage
    public void onMessage(Session session,String msg){
        System.out.println(msg);
        JSONObject j=JSON.parseObject(msg);
        String messageFrom=(String)j.get("messageFrom");
        String messageTo=(String)j.get("messageTo");
        String messageContent=(String)j.get("messageContent");
        String contentType=(String)j.get("contentType");
        this.socketId=messageFrom;
        try{
            //存入redis
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("messageFrom",messageFrom);
            jsonObject.put("messageContent",messageContent);
            jsonObject.put("contentType",contentType);
            jsonObject.put("createTime",new Date());
            JedisPool pool=RedisAPI.getPool();
            Jedis jedis=pool.getResource();
            jedis.lpush(messageTo,jsonObject.toString());
            pool.returnResource(jedis);
            //存入数据库
            MessageEntity message=new MessageEntity();
            String uuid = UUID.randomUUID().toString().replace("-", "");
            message.setId(uuid);
            message.setContent(messageContent);
            message.setTime(new Timestamp(new Date().getTime()));
            message.setContentType(Integer.parseInt(contentType));
            message.setFromUserId(messageFrom);
            message.setToUserId(messageTo);
            messageRepository.save(message);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    @OnError
     public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
    public String getSocketId(){
        return this.socketId;
    }
    public Session getSession(){
        return this.session;
    }
}
*/