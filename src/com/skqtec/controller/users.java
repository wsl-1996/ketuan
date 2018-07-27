package com.skqtec.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.UserEntity;
import com.skqtec.repository.SendAddressRepository;
import com.skqtec.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/applet/users")
public class users {

    static Logger logger = Logger.getLogger(users.class.getName());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SendAddressRepository sendAddressRepository;
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



    /**
     * 获取用户详细信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/getdetail",method=RequestMethod.GET)
    public @ResponseBody ResponseData getuser(HttpServletRequest request, HttpServletResponse response){
        ResponseData responseData = new ResponseData();
        String usersid = request.getParameter("userid");
        try {
            UserEntity user= userRepository.get(usersid);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user",user);
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
    //获取用户账户等级
    @RequestMapping(value="/getusergrade",method=RequestMethod.GET)
    public @ResponseBody ResponseData getUserGrade(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String userId = request.getParameter("userid");
        try {
            UserEntity user = userRepository.get(userId);
            String userGrade = String.valueOf(user.getGrade());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userGrade", userGrade);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_USER_GRADE_FAILED);
        } finally {
            return responseData;
        }
    }
    //获取children信息
    @RequestMapping(value="/getchildren",method=RequestMethod.GET)
    public @ResponseBody ResponseData getChildren(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String userId=request.getParameter("userid");
        try {
            UserEntity user=userRepository.get(userId);
            String childrens=user.getChildren();
            JSONArray jChildrens = JSONArray.parseArray(childrens);
            List<JSONObject>jsonObject1=new ArrayList<JSONObject>();
            for(int i=0;i<jChildrens.size();i++){
                String childId=jChildrens.getString(i);
                UserEntity child=userRepository.get(childId);
                String nickName=child.getNickname();
                String headImg=CommonMessage.IMG_URL+child.getHeadImgUrl();
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("nickName",nickName);
                jsonObject.put("headImg",headImg);
                jsonObject1.add(jsonObject);
            }
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("childrenInfo",jsonObject1);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_CHILDREN_FAILED);
        } finally {
            return responseData;
        }
    }

}
