package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.GroupEntity;
import com.skqtec.repository.GroupRepository;
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
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/applet/groups")
public class groups {

    static Logger logger = Logger.getLogger(groups.class.getName());

    @Autowired
    private GroupRepository groupRepository;

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
        String name = request.getParameter("groupbuy_name");
        String info = request.getParameter("groupbuy_info");
        String productid = request.getParameter("productid");
        String ownerid = request.getParameter("ownerid");
        String count = request.getParameter("groupbuy_count");
        String price = request.getParameter("groupbuy_price");
        String cost = request.getParameter("groupbuy_cost");
        String deliver_add = request.getParameter("groupbuy_deliver_add");
        String firstImg = request.getParameter("productFirstImg");
        String[] slideimgs = request.getParameterValues("productSlideimgs[]");
        String endtime = request.getParameter("groupbuy_end_time");
        String starttime = request.getParameter("groupbuy_start_time");
        GroupEntity groupEntity = new GroupEntity();
        groupEntity.setGroupName(name);
        groupEntity.setGroupDiscription(info);
        groupEntity.setProductId(productid);
        groupEntity.setUserId(ownerid);
        groupEntity.setGroupPrice(Double.parseDouble(price));
        groupEntity.setProductCost(Double.parseDouble(cost));
        groupEntity.setDeliverAddress(deliver_add);
        groupEntity.setGroupCount(Integer.parseInt(count));
        groupEntity.setGroupFirstImg(firstImg);
        groupEntity.setEndTime(Timestamp.valueOf(endtime));
        groupEntity.setGroupSlideImg(JSON.toJSONString(slideimgs));
        String uuid = UUID.randomUUID().toString().replace("-", "");
        groupEntity.setId(uuid);
        try{
            logger.info("********product save returned :  "+groupRepository.save(groupEntity));
            JSONObject data = new JSONObject();
            data.put("groupid",uuid);
            responseData.setData(data);
        }
        catch (Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.CREATE_GROUP_FAILED);
        }
        finally {
            return responseData;
        }
    }

    /**
     * 获取所有团购
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/listall",method=RequestMethod.GET)
    public @ResponseBody ResponseData getAllGroup(HttpServletRequest request, HttpServletResponse response){
        ResponseData responseData = new ResponseData();
        try {
            List<GroupEntity> groups = groupRepository.findAll();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("groups",groups);
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
     * @param response
     * @return
     */
    @RequestMapping(value="/search",method=RequestMethod.GET)
    public @ResponseBody ResponseData queryGroups(HttpServletRequest request, HttpServletResponse response){
        ResponseData responseData = new ResponseData();
        String key = request.getParameter("key");
        try {
//            JSONObject queryObject = new JSONObject();
//            queryObject.put("key",key);
            List<GroupEntity> groups = groupRepository.search(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("groups",groups);
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
