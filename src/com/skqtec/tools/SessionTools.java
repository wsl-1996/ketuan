package com.skqtec.tools;

import redis.clients.jedis.Jedis;

import java.util.UUID;

public class SessionTools {
    //查询session
    public String sessionQuery(String sessionId){
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        String userId=jedis.get(sessionId);
        if(userId!=null)
        jedis.expire(sessionId,600);
        return userId;
    }
    public String setSession(String userId){
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        jedis.set(uuid,userId);
        jedis.expire(uuid,600);
        return uuid;
    }

}
