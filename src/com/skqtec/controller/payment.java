package com.skqtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.OrderEntity;
import com.skqtec.entity.UserEntity;
import com.skqtec.repository.OrderRepository;
import com.skqtec.repository.UserRepository;
import com.skqtec.wxtools.WXPay;
import com.skqtec.wxtools.WXPayConfigImpl;
import com.skqtec.wxtools.WXPayUtil;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Controller
public class payment {
    private WXPay wxpay;
    private WXPayConfigImpl config;
    private static Logger logger= Logger.getLogger(payment.class);
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private UserRepository userRepository;
    //支付请求接口
    @RequestMapping(value="/payrequest",method=RequestMethod.GET)
    public @ResponseBody ResponseData payRequest(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        //String userId="o5sMv0TY78EzXxCuFQF_nLH8e6YQ";
        String sessionId=request.getParameter("sessionId");
        String productId=request.getParameter("productid");
        String out_trade_no=request.getParameter("orderid");
        String fee=request.getParameter("fee");
        //判断是否登录
        String userId="957a2c423b7e43828bbee771fdcbb8ed";//SessionTools.sessionQuery(sessionId);
        if(userId==null){
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
            return responseData;
        }
        UserEntity user=userRepository.get(userId);
        String openId=user.getOpenid();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "克团-XXX");
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", "APP");
        data.put("fee_type", "CNY");
        data.put("total_fee", fee);
        data.put("spbill_create_ip", "114.212.81.63");
        data.put("notify_url", "a73f284b.ngrok.io/ketuan/paycallback");
        data.put("trade_type", "JSAPI");
        data.put("product_id", productId);
        data.put("openid",openId);
        Map<String, String> r=new HashMap<String, String>();
        try {
            this.config=WXPayConfigImpl.getInstance();
            this.wxpay=new WXPay(config);
            r = wxpay.unifiedOrder(data);
            System.out.println(r);
            JSONObject jsonobject=new JSONObject();
            jsonobject.put("data",r);
            responseData.setData(jsonobject);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return responseData;
        }
    }

    //支付回调接口
    @RequestMapping(value="/paycallback",method=RequestMethod.POST)
    public @ResponseBody String payCallBack(@RequestBody String body){
        Map<String,String>info=new HashMap<String, String>();
        String returns=null;
        try {
            info = WXPayUtil.xmlToMap(body);
            String orderId=info.get("out_trade_no");
            int totalFee=Integer.parseInt(info.get("total_fee"));
            String payTime=info.get("time_end");
            OrderEntity order=orderRepository.get(orderId);
            if(totalFee!=order.getTotalPrice()) {
                returns = "<xml>" +
                        "  <return_code><![CDATA[FAIL]]></return_code>" +
                        "  <return_msg><![CDATA[OK]]></return_msg>" +
                        "</xml>";
            } else {
                returns = "<xml>" +
                        "  <return_code><![CDATA[SUCCESS]]></return_code>" +
                        "  <return_msg><![CDATA[OK]]></return_msg>" +
                        "</xml>";
                order.setState(2);
                order.setPayTime(Timestamp.valueOf(payTime));
                orderRepository.saveOrUpdate(order);
            }
        }catch(Exception e){
            e.printStackTrace();
            returns="<xml>" +
                    "  <return_code><![CDATA[FAIL]]></return_code>" +
                    "  <return_msg><![CDATA[OK]]></return_msg>" +
                    "</xml>";
        }finally{
            return returns;
        }
    }
}
