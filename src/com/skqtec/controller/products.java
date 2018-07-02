package com.skqtec.controller;

import com.skqtec.repository.ProductRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/applet/products")
public class products {

    static Logger logger = Logger.getLogger(products.class.getName());

    @Autowired
    private ProductRepository productdao;

    /***
     * 管理页面上传图片（买家秀页面上传图片也用该接口）
     * @return
     */
    @RequestMapping("/create")
    public @ResponseBody
    String upload(){
        return "";
    }




}
