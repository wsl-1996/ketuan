package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.ImageEntity;
import com.skqtec.repository.ImageRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class images {

    static Logger logger = Logger.getLogger(images.class.getName());

    @Autowired
    private ImageRepository imagedao;

    @RequestMapping("/hello")
    public @ResponseBody String hello() {
        ImageEntity image = new ImageEntity();
        image.setUrl("/fdsa/f");
        image.setDiscription("fasdfasdad");
        logger.info("********sava returned :  "+imagedao.save(image));
        imagedao.flush();
        logger.error("hello join skqtec.");
        return "hello";
    }

    @RequestMapping("/test")
    public @ResponseBody String test() {
        ImageEntity image = imagedao.load(2);
        System.out.println(image.getUrl().toString());
        String result = JSON.toJSONString(image);
        logger.info(result);
        return result;
    }

    @RequestMapping("/test1")
    public @ResponseBody String test1() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url","/fdsa/f");
        List<ImageEntity> images = imagedao.query(jsonObject);
        String result = JSON.toJSONString(images);
        logger.info(result);
        return result;
    }
}
