package com.skqtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.CommentEntity;
import com.skqtec.entity.OrderEntity;
import com.skqtec.entity.UserEntity;
import com.skqtec.repository.CommentRepository;
import com.skqtec.repository.OrderRepository;
import com.skqtec.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/applet/comments")
public class comments {
    static Logger logger = Logger.getLogger(comments.class.getName());
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderRepository orderRepository;
    //获取某商品的评价信息
    @RequestMapping(value="/getcommentlist",method=RequestMethod.GET)
    public @ResponseBody ResponseData getComments(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        String page=request.getParameter("page");
        String productId=request.getParameter("productid");
        try{
            List<CommentEntity>comments=new ArrayList<CommentEntity>();
            comments=commentRepository.query(page,productId);
            List<JSONObject>jsonObject=new ArrayList<JSONObject>();
            for(CommentEntity comment:comments){
                JSONObject j=new JSONObject();
                UserEntity user=userRepository.get(comment.getUserId());
                OrderEntity order=orderRepository.get(comment.getId());
                String userImg=CommonMessage.IMG_URL+user.getHeadImgUrl();
                String userName=user.getNickname();
                String starLevel=String.valueOf(comment.getStarLevel());
                String commentTime=String.valueOf(comment.getCommentTime());
                String commentContent=comment.getCommentContent();
                String commentImg=CommonMessage.IMG_URL+comment.getAttachImgs();
                //String
                String payTime=String.valueOf(order.getPayTime());
                j.put("headImg",userImg);
                j.put("userName",userName);
                j.put("starLevel",starLevel);
                j.put("commentTime",commentTime);
                j.put("commentContent",commentContent);
                j.put("commentImg",commentImg);
               // j.put("",);
                j.put("payTime",payTime);
                jsonObject.add(j);
            }
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("commentList",jsonObject);
            responseData.setData(jsonObject1);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_COMMENT_LIST_FAILED);
        }finally{
            return responseData;
        }
    }
}
