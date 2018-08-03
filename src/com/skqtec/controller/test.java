package com.skqtec.controller;

import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.wxtools.WXPayConstants;
import com.skqtec.wxtools.WXPayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/applet")
public class test {
    @RequestMapping(value="/test",method=RequestMethod.GET)
    public @ResponseBody
    ResponseData login(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            Map<String,String> reqData=new HashMap<String, String>();
            reqData.put("appId","wx5733cafea467c980");
            reqData.put("nonceStr","fjVOtMdZ9f0ALW74");
            reqData.put("package","prepay_id=wx03174838573134b762d0256c3218565446");
            reqData.put("signType","MD5");
            reqData.put("timeStamp","1533289949");
            String paySign=WXPayUtil.generateSignature(reqData,"lijie1987110801spaceorg1234qwerl",WXPayConstants.SignType.MD5);
            System.out.println(paySign);
        } catch (Exception e) {
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.TEST_FAILED);
        } finally {
            return responseData;
        }
    }
}
