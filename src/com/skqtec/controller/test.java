package com.skqtec.controller;

import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.tools.RedisAPI;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
@Controller
@RequestMapping("/applet")
public class test {
    @RequestMapping(value="/test",method=RequestMethod.GET)
    public @ResponseBody
    ResponseData login(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            JedisPool jedisPool=RedisAPI.getPool();
            Jedis jedis=jedisPool.getResource();
            jedis.set("aaa","aaa");

            System.out.println(jedis.get("aaa"));
            jedisPool.returnResource(jedis);
        } catch (Exception e) {
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.TEST_FAILED);
        } finally {
            return responseData;
        }
    }
}
