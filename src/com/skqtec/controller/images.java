package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.entity.ImageEntity;
import com.skqtec.repository.ImageRepository;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@RequestMapping("/applet/images")

@Controller
public class images {

    static Logger logger = Logger.getLogger(images.class.getName());

    @Autowired
    private ImageRepository imagedao;



    @RequestMapping("/hello")
    public @ResponseBody String hello() {
        ImageEntity image = new ImageEntity();
        image.setUrl("/fdsa/f");
        image.setDiscription("fasdfasdad");
        logger.info("********sava returned :  "+imagedao.save(image));
        imagedao.flush();
        logger.error("hello join skqtec.");
        return "hello";
    }

    @RequestMapping("/test")
    public @ResponseBody String test() {
        ImageEntity image = imagedao.load("2");
        System.out.println(image.getUrl().toString());
        String result = JSON.toJSONString(image);
        logger.info(result);
        return result;
    }

    @RequestMapping("/test1")
    public @ResponseBody String test1() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url","/fdsa/f");
        List<ImageEntity> images = imagedao.query(jsonObject);
        String result = JSON.toJSONString(images);
        logger.info(result);
        return result;
    }

    /***
     * 管理页面上传图片（买家秀页面上传图片也用该接口）
     * @return
     */
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public @ResponseBody String upload(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> ids = new ArrayList<String>();
        String uploadPath = request.getRealPath("/")+"imagesDir\\";
        File tempPathFile = new File("d:\\tempPath\\");
        try {
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Set factory constraints
            factory.setSizeThreshold(4096); // 设置缓冲区大小，这里是4kb
            factory.setRepository(tempPathFile);// 设置缓冲区目录

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);
            // Set overall request size constraint
            upload.setSizeMax(4194304); // 设置最大文件尺寸，这里是4MB

            List<FileItem> items = upload.parseRequest(request);// 得到所有的文件
            Iterator<FileItem> i = items.iterator();
            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                String fileName = fi.getName();
                String fieldName = fi.getFieldName();
                if ("file".equals(fieldName)) {
                    ImageEntity image = new ImageEntity();
                    String discription = fileName;
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    ids.add(uuid);
                    image.setId(uuid);
                    String imageName = uuid+".png";
                    String url = "http://www.ketuan.com/imagesDir/"+imageName;
                    image.setUrl(url);
                    image.setDiscription(discription);
                    logger.info("********sava returned :  "+imagedao.save(image));
                    //File fullFile = new File(new String(fi.getName().getBytes(), "utf-8")); // 解决文件名乱码问题
                    File savedFile = new File(uploadPath, imageName);
                    fi.write(savedFile);
                }
            }
            System.out.print("upload succeed");
        } catch (Exception e) {
            logger.error(e,e.fillInStackTrace());
        }
        return JSON.toJSONString(ids);
    }

    /**
     * 根据图片id获取图片信息
     * @return
     */
    @RequestMapping("/getimagebyid")
    public @ResponseBody String getImgById(){
        return "";
    }

    @RequestMapping("/save")
    public String Save(@ModelAttribute("form") String user, Model model) { // user:视图层传给控制层的表单对象；model：控制层返回给视图层的对象
        model.addAttribute("user", user);
        return "detail";
    }
}
