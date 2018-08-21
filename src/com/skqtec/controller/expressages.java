
package com.skqtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.ExpressageEntity;
import com.skqtec.entity.ProductEntity;
import com.skqtec.repository.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/applet/expressages")
public class expressages {
    static Logger logger = Logger.getLogger(orders.class.getName());

    @Autowired
    private OrderRepository orderRepository;


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ExpressageRepository expressageRepository;
    @Autowired
    private TrackRepository trackRepository;


/**
     * 获取所有快递
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value="/listall",method=RequestMethod.GET)
    public @ResponseBody ResponseData getAllExpressages(HttpServletRequest request, HttpServletResponse response){
        ResponseData responseData = new ResponseData();
        try {
            List<ExpressageEntity> expressages = expressageRepository.findAll();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("expressages",expressages);
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
     * 关键词查询快递
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public @ResponseBody ResponseData queryExpressages(HttpServletRequest request, HttpServletResponse response){
        ResponseData responseData = new ResponseData();
        String key = request.getParameter("key");
        try {
            List<ExpressageEntity> expressages = expressageRepository.search(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("expressages",expressages);
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




    //获取快递详情
    @RequestMapping(value="/getexpressagedetails",method = RequestMethod.GET)
    public @ResponseBody ResponseData getExpressageDetails(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String expressageId = request.getParameter("expressageid");
        try {
            ExpressageEntity expressage=expressageRepository.get(expressageId);
            ProductEntity product=productRepository.get(expressage.getProductId());
            String expressageIsnew=null;
            switch(expressage.getIsNew()){
                case 1:expressageIsnew="已最新";break;
                case 2:expressageIsnew="待更新";break;
            }
            String expressageName=expressage.getExpressageName();
            String shipAddress=expressage.getShipAddress();
            String addressOfSevvice=expressage.getAddressOfSevvice();
            String productId=product.getId();
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("expressageIsnew",expressageIsnew);
            jsonObject.put("expressageName",expressageName);
            jsonObject.put("shipAddress",shipAddress);
            jsonObject.put("addressOfSevvice",addressOfSevvice);
            jsonObject.put("productId",productId);
            jsonObject.put("expressageDetails",expressage.getExpressageDetails());
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("expressage",expressage);
            responseData.setData(jsonObject1);
        } catch (Exception e) {
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_ORDER_DETAILS_FAILED);
        } finally {
            return  responseData;
        }
    }
    //获取快递轨迹
    @RequestMapping(value="/gettrack",method = RequestMethod.GET)
    public @ResponseBody ResponseData getTrack(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String orderId = request.getParameter("orderid");
        try {
            String trackId=orderRepository.get(orderId).getTrackId();
            JSONObject jsonObject=new JSONObject();
            String track=trackRepository.find(trackId);
            jsonObject.put("track",track);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e, e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_TRACK_FAILED);
        } finally {
            return responseData;
        }

    }












    }










