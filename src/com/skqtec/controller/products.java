package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.ProductEntity;
import com.skqtec.repository.ProductRepository;
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
@RequestMapping("/applet/products")
public class products {

    static Logger logger = Logger.getLogger(products.class.getName());

    @Autowired
    private ProductRepository productRepository;

    /***
     * 管理页面上传图片（买家秀页面上传图片也用该接口）
     * @return
     */
    @RequestMapping()
    public @ResponseBody
    String upload(){
        return "";
    }

    /**
     * 创建产品
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/create",method=RequestMethod.POST)
    public @ResponseBody ResponseData createProduct(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        String name = request.getParameter("productname");
        String info = request.getParameter("productinfo");
        String code = request.getParameter("productcode");
        String lable = request.getParameter("productlable");
        String ownertype = request.getParameter("productownertype");
        String ownerid = request.getParameter("productownerid");
        String count = request.getParameter("productcount");
        String price = request.getParameter("productprice");
        String cost = request.getParameter("productcost");
        String produceaddress = request.getParameter("productproduceadd");
        String packstand = request.getParameter("productpackstand");
        String aftersale = request.getParameter("productaftersale");
        String firstimg = request.getParameter("productFirstImg");
        String[] slideimgs = request.getParameterValues("productSlideimgs[]");
        String[] contentimgs = request.getParameterValues("productContentimgs[]");
        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(name);
        productEntity.setProductInfo(info);
        productEntity.setProductClassifyCode(code);
        productEntity.setProductLabel(lable);
        int ownerType = Integer.parseInt(ownertype);
        productEntity.setOwnerType(ownerType);
        if(ownerType==0){
            productEntity.setMerchantId(ownerid);
        }else{
            productEntity.setUserId(ownerid);
        }
        productEntity.setPrice(Double.parseDouble(price));
        productEntity.setProductCost(Double.parseDouble(cost));
        productEntity.setProductProduceAddress(produceaddress);
        productEntity.setPackStand(packstand);
        productEntity.setAfterSale(aftersale);
        productEntity.setProductFistImg(firstimg);
        productEntity.setProductSlideImg(JSON.toJSONString(slideimgs));
        productEntity.setImagesAddress(JSON.toJSONString(contentimgs));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        productEntity.setId(uuid);
        try{
            logger.info("********product save returned :  "+ productRepository.save(productEntity));
            JSONObject data = new JSONObject();
            data.put("productid",uuid);
            responseData.setData(data);
        }
        catch (Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage("创建商品失败！");
        }
        finally {
            return responseData;
        }
    }

    /**
     * 获取所有团购
     * @return
     */
    @RequestMapping(value="/listall",method=RequestMethod.GET)
    public @ResponseBody ResponseData getAllProducts(){
        ResponseData responseData = new ResponseData();
        try {
            List<ProductEntity> products = productRepository.findAll();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("products",products);
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
     * 关键词查询团购
     * @param request
     * @return
     */
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public @ResponseBody ResponseData queryProducts(HttpServletRequest request){
        ResponseData responseData = new ResponseData();
        String key = request.getParameter("key");
        try {
            List<ProductEntity> products = productRepository.search(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("products",products);
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
