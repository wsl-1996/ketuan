package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.MessageEntity;
import com.skqtec.repository.MessageRepository;
import com.skqtec.tools.RedisAPI;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/applet/message")
public class message {
    static Logger logger = Logger.getLogger(message.class.getName());
    @Autowired
    private MessageRepository messageRepository;
    @RequestMapping("/sendMessage")
    public @ResponseBody ResponseData sendMessages(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String data=request.getParameter("data");
        JSONObject j=JSON.parseObject(data);
        String messageFrom=(String)j.get("messageFrom");
        String messageTo=(String)j.get("messageTo");
        String messageContent=(String)j.get("messageContent");
        String contentType=(String)j.get("contentType");
        String headOwner=(String)j.get("headOwner");
        try{
            //存入redis
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("messageFrom", messageFrom);
            jsonObject.put("messageContent", messageContent);
            jsonObject.put("contentType", contentType);
            jsonObject.put("headOwner",headOwner);
            DateFormat sdf = new SimpleDateFormat("MM月dd日 HH:mm");
            String date=sdf.format(new Date());
            jsonObject.put("createTime", date);
            JedisPool pool = RedisAPI.getPool();
            Jedis jedis = pool.getResource();
            String jsonStr=jsonObject.toString();
            jsonStr.replace("{\"","{'");
            jsonStr.replace("\":","':");
            jsonStr.replace(",\"",",'");
            //System.out.println(jsonStr);
            jedis.lpush(messageTo, jsonStr);
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
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.SEND_MESSAGE_FAILED);
        }finally {
            return responseData;
        }
    }
}
