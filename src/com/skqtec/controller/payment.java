package com.skqtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.ResponseData;
import com.skqtec.wxtools.WXPayConfigImpl;
import com.skqtec.wxtools.WXPayUtil;
import com.sun.istack.internal.logging.Logger;
import org.springframework.stereotype.Controller;
import com.skqtec.wxtools.WXPay;
import org.springframework.web.bind.annotation.*;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;
@Controller
public class payment {
    private WXPay wxpay;
    private WXPayConfigImpl config;
    private static Logger logger= Logger.getLogger(payment.class);
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
    //支付请求接口
    @RequestMapping(value="/payrequest",method=RequestMethod.GET)
    public @ResponseBody ResponseData payRequest(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        String userId="o5sMv0TY78EzXxCuFQF_nLH8e6YQ";
        //String userId=request.getParameter("userid");
        String productId=request.getParameter("productid");
        String fee=request.getParameter("fee");
        HashMap<String, String> data = new HashMap<String, String>();
        String out_trade_no=WXPayUtil.getOrderNo();
        data.put("body", "克团-XXX");
        data.put("out_trade_no", out_trade_no);
        data.put("device_info", "APP");
        data.put("fee_type", "CNY");
        data.put("total_fee", fee);
        data.put("spbill_create_ip", "114.212.81.63");
        data.put("notify_url", "a73f284b.ngrok.io/ketuan/paycallback");
        data.put("trade_type", "NATIVE");
        data.put("product_id", productId);
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
    @RequestMapping(value="/paycallback",method=RequestMethod.GET)
    public @ResponseBody ResponseData payCallBack(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        //String strxml=request.get
        return responseData;
    }


}
