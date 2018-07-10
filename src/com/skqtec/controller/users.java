package com.skqtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.UserEntity;
import com.skqtec.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/applet/users")
public class users {

    static Logger logger = Logger.getLogger(users.class.getName());

    @Autowired
    private UserRepository userRepository;

    /**
     * 获取所有用户
     * @return
     */
    @RequestMapping(value="/listall",method=RequestMethod.GET)
    public @ResponseBody ResponseData getAllUsers(){
        ResponseData responseData = new ResponseData();
        try {
            List<UserEntity> users = userRepository.findAll();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",users);
            responseData.setData(jsonObject);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_GROUP_LIST_FAILED);
        }
        finally {
            return responseData;
        }
    }

    /**
     * 关键词查询用户
     * @param request
     * @return
     */
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public @ResponseBody ResponseData queryUsers(HttpServletRequest request){
        ResponseData responseData = new ResponseData();
        String key = request.getParameter("key");
        try {
            List<UserEntity> users = userRepository.search(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",users);
            responseData.setData(jsonObject);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_GROUP_LIST_FAILED);
        }
        finally {
            return responseData;
        }
    }

}
