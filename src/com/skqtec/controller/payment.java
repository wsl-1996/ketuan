package com.skqtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.OrderEntity;
import com.skqtec.repository.OrderRepository;
import com.skqtec.repository.UserRepository;
import com.skqtec.tools.SessionTools;
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
@RequestMapping("/applet/payments")
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
    //支付请求
    public static JSONObject payRequest(String orderId,String productId,String openId,String fee){
       // ResponseData responseData=new ResponseData();
        //String userId="o5sMv0TY78EzXxCuFQF_nLH8e6YQ";
        //String sessionId=request.getParameter("sessionid");
        //String productId=request.getParameter("productid");
        //String orderId=request.getParameter("orderid");
        //String fee=request.getParameter("fee");
        //判断是否登录
        /*String userId=SessionTools.sessionQuery(sessionId);
        if(userId==null){
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
            return responseData;
        }
        */
       // UserEntity user=userRepository.get(userId);
        //OrderEntity order=orderRepository.get(orderId);
        //String fee=String.valueOf(order.getTotalPrice());
        //String productId=order.getProductId();
        //String openId=user.getOpenid();
        JSONObject jsonobject=new JSONObject();
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("body", "克团-XXX");
        data.put("out_trade_no", orderId);
        data.put("device_info", "APP");
        data.put("fee_type", "CNY");
        data.put("total_fee", fee);
        data.put("spbill_create_ip", "114.212.81.63");
        data.put("notify_url", "f6677120.ngrok.io/ketuan/paycallback");
        data.put("trade_type", "JSAPI");
        data.put("product_id", productId);
        data.put("openid",openId);
        Map<String, String> r=new HashMap<String, String>();
        try {
            WXPayConfigImpl config=WXPayConfigImpl.getInstance();
            WXPay wxpay=new WXPay(config);
            r = wxpay.unifiedOrder(data);
            System.out.println(r);
            jsonobject.put("data",r);
            //responseData.setData(jsonobject);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            return jsonobject;
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
    //申请退款接口
    @RequestMapping(value="/refund",method=RequestMethod.GET)
    public @ResponseBody ResponseData refund(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String sessionId = request.getParameter("sessionid");
        String orderId = request.getParameter("orderid");
        try {
            //判断是否登录
            String userId=SessionTools.sessionQuery(sessionId);
            if(userId==null){
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
                return responseData;
            }
            String outRefundNo=WXPayUtil.getOrderNo();
            OrderEntity order=orderRepository.get(orderId);
            String totalFee=String.valueOf(order.getTotalPrice());
            order.setOutRefundNo(outRefundNo);
            HashMap<String, String> data = new HashMap<String, String>();
            data.put("out_trade_no",orderId);
            data.put("out_refund_no",outRefundNo);
            data.put("total_fee",totalFee);
            data.put("refund_fee",totalFee);
            Map<String, String> r=new HashMap<String, String>();
            //data.put("",);
            this.config=WXPayConfigImpl.getInstance();
            this.wxpay=new WXPay(config);
            r=wxpay.refund(data);
            if(!r.get("return_code").equals("SUCCESS"));{
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.REFUND_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.REFUND_FAILED);
        } finally {
            return responseData;
        }
    }
}
