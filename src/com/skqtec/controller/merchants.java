package com.skqtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.MerchantEntity;
import com.skqtec.repository.MerchantRepository;
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
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/applet/merchants")
public class merchants {

    static Logger logger = Logger.getLogger(merchants.class.getName());

    @Autowired
    private MerchantRepository merchantRepository;

    /**
     * 创建商家
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/create",method=RequestMethod.POST)
    public @ResponseBody ResponseData createProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        String name = request.getParameter("merchant_name");
        String addresss = request.getParameter("merchant_add");
        String phone = request.getParameter("merchant_phone");
        String discription = request.getParameter("merchant_info");
        String count = request.getParameter("merchant_count");
        String pass = request.getParameter("merchant_pass");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("accountname",count);
        List<MerchantEntity> mechants = merchantRepository.query(jsonObject);
        if(mechants.size()>0){
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.MERCHANT_ACOUNT_EXISTS);
            return responseData;
            //return JSON.toJSONString(responseData);
        }
        MerchantEntity merchantEntity = new MerchantEntity();
        merchantEntity.setName(name);
        merchantEntity.setAddress(addresss);
        merchantEntity.setPhone(phone);
        merchantEntity.setDiscription(discription);
        merchantEntity.setAccountname(count);
        merchantEntity.setAccoutpass(pass);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        merchantEntity.setId(uuid);
        try{
            logger.info("********product save returned :  "+ merchantRepository.save(merchantEntity));
            JSONObject data = new JSONObject();
            data.put("merchantid",uuid);
            responseData.setData(data);
        }
        catch (Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.SAVE_MERCHANT_INFO_FAILED);
        }
        finally {
            return  responseData;
            //return JSON.toJSONString(responseData);
        }
    }
}
