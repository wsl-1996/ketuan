package com.skqtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.OrderEntity;
import com.skqtec.repository.OrderRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/applet/orders")
public class orders {
    static Logger logger = Logger.getLogger(orders.class.getName());

    @Autowired
    private OrderRepository orderRepository;



    //生成订单
    @RequestMapping(value="/creatorder",method = RequestMethod.GET)
    public @ResponseBody ResponseData createProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseData responseData=new ResponseData();
        String productId=request.getParameter("");
        String groupId=request.getParameter("");
        String userId=request.getParameter("");
        String state=request.getParameter("");
        String sendName=request.getParameter("");
        String sendAddress=request.getParameter("");
        String sendZip=request.getParameter("");
        String sendTel=request.getParameter("");
        String orderTime=request.getParameter("");
        String paymethod=request.getParameter("");
        String meno=request.getParameter("");
        String totalPrice=request.getParameter("");
        String trackCode=request.getParameter("");
        String trackId=request.getParameter("");
        String payTime=request.getParameter("");
        String deliverTime=request.getParameter("");
        String receiptTime=request.getParameter("");
        String productPrice=request.getParameter("");
        String carriagePrice=request.getParameter("");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        OrderEntity orderEntity=new OrderEntity();
        /*
        orderEntity.setCarriagePrice();
        orderEntity.setDeliverTime();
        orderEntity.setGroupId();
        orderEntity.setId(uuid);
        orderEntity.setMeno();
        orderEntity.setOrderTime();
        orderEntity.setProductId();
        orderEntity.setProductPrice();
        orderEntity.setSendName();
        orderEntity.setSendTel();
        orderEntity.setSendZip();
        orderEntity.setState();
        orderEntity.setTrackCode();
        orderEntity.setUserId();
        */
        try{
            logger.info("********product save returned :  "+orderRepository.save(orderEntity));
            JSONObject data = new JSONObject();
            data.put("orderid",uuid);
            responseData.setData(data);
        }
        catch (Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.CREATE_ORDER_FAILED);
        }
        finally {
            return responseData;
        }




    }
    //获取订单
    @RequestMapping(value="/getorder",method = RequestMethod.GET)
    public @ResponseBody ResponseData getOrder(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        String userId=request.getParameter("userid");
        String orderState=request.getParameter("orderstate");
        try{
            List<OrderEntity>orders=new ArrayList<OrderEntity>();
            orders=orderRepository.query(userId,orderState);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("orders",orders);
            responseData.setData(jsonObject);
        }catch(Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_ORDER_FAILED);
        }finally{
            return responseData;
        }
    }
    //删除订单
    @RequestMapping(value="/removeorder",method = RequestMethod.GET)
    public @ResponseBody ResponseData removeOrder(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        String orderId=request.getParameter("orderid");
        try{
           orderRepository.delete(orderId);
        }catch(Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_ORDER_FAILED);
        }finally{
            return responseData;
        }
    }
   /* //订单搜索
    @RequestMapping(value="/removeorder",method = RequestMethod.GET)
    public @ResponseBody ResponseData removeOrder(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        String orderId=request.getParameter("orderid");
        try{
            orderRepository.delete(orderId);
        }catch(Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_ORDER_FAILED);
        }finally{
            return responseData;
        }
    }*/




}
