
package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.*;
import com.skqtec.repository.*;
import com.skqtec.tools.DisposeUtil;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/backmanage")
public class backmanage {
    static Logger logger = Logger.getLogger(groups.class.getName());

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MerchantRepository merchantRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private  ExpressageRepository  expressageRepository;

    /**
     * 创建产品
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "groups/addgroup", method = RequestMethod.GET)
    public @ResponseBody
    ResponseData createProduct(HttpServletRequest request, HttpServletResponse response)
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
        try {
            logger.info("********product save returned :  " + groupRepository.save(groupEntity));
            JSONObject data = new JSONObject();
            data.put("groupid", uuid);
            responseData.setData(data);
        } catch (Exception e) {
            logger.error(e, e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.CREATE_GROUP_FAILED);
        } finally {
            return responseData;
        }
    }

    /**
     * 获取所有团购
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/grouplistall",method=RequestMethod.GET)
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
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/groupsearch", method = RequestMethod.GET)
    public @ResponseBody
    ResponseData queryGroups(HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        String key = request.getParameter("key");
        try {
//            JSONObject queryObject = new JSONObject();
//            queryObject.put("key",key);
            List<GroupEntity> groups = groupRepository.search(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("groups", groups);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_GROUP_LIST_FAILED);
        } finally {
            return responseData;
        }
    }



    //获取团购详情
    @RequestMapping(value="/getgroupinfo",method=RequestMethod.GET)
    public @ResponseBody ResponseData getGroupDetails(HttpServletRequest request,HttpServletResponse response){
        ResponseData responseData=new ResponseData();
        String groupId=request.getParameter("groupid");
        try {
            GroupEntity group = groupRepository.get(groupId);
            JSONObject jsonobject = new JSONObject();
            jsonobject.put("groupdetails",group);
            responseData.setData(jsonobject);
        }catch(Exception e){
            logger.error(e.getMessage(),e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_GROUP_DETAILS_FAILED);
        }finally{
            return responseData;
        }
    }


    /**
     * 实现对图片的表操作
     */



    /**
     * 获取所有订单
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/orderlistall",method=RequestMethod.GET)
    public @ResponseBody ResponseData getAllOrders(HttpServletRequest request, HttpServletResponse response){
        ResponseData responseData = new ResponseData();
        try {
            List<OrderEntity> orders = orderRepository.findAll();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orders",orders);
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



    //订单搜索
    @RequestMapping(value="/searchorders",method = RequestMethod.GET)
    public @ResponseBody ResponseData searchOrder(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        String userId=request.getParameter("userid");
        String key=request.getParameter("key");
        try{
            List<JSONObject>j=new ArrayList<JSONObject>();
            List<OrderEntity>orders=new ArrayList<OrderEntity>();
            orders=orderRepository.search(userId,key);
            for(OrderEntity order:orders){
                ProductEntity product=productRepository.get(order.getProductId());
                String shopName=null;
                if(product.getOwnerType()==0) {
                    MerchantEntity merchant = merchantRepository.get(product.getMerchantId());
                    shopName=merchant.getName();
                }
                else{
                    UserEntity user=userRepository.get(product.getUserId());
                    shopName=user.getNickname();
                }
                String orderId=order.getId();
                String orderState=null;
                switch(order.getState()){
                    case 1:orderState="待付款";break;
                    case 2:orderState="待发货";break;
                    case 3:orderState="待收货";break;
                    case 4:orderState="待评价";break;
                    case 5:orderState="已评价";break;
                }
                String productImg=CommonMessage.IMG_URL+product.getProductFistImg();
                String productTitle=product.getProductName();
                String productPrice=String.valueOf(product.getPrice());
                String sums=String.valueOf(order.getSums());
                String typeSpecification=order.getTypeSpecification();
                String sumPrice=String.valueOf(order.getTotalPrice());
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("shopName",shopName);
                jsonObject.put("orderId",orderId);
                jsonObject.put("orderState",orderState);
                jsonObject.put("productImg",productImg);
                jsonObject.put("productTitle",productTitle);
                jsonObject.put("productPrice",productPrice);
                jsonObject.put("sums",sums);
                jsonObject.put("typeSpecification",typeSpecification);
                jsonObject.put("sumPrice",sumPrice);
                j.add(jsonObject);
            }
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("searchResult",j);
            responseData.setData(jsonObject1);
        }catch(Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.SEARCH_ORDERS_FAILED);
        }finally{
            return responseData;
        }
    }


    //获取订单详情
    @RequestMapping(value = "/getorderdetails", method = RequestMethod.GET)
    public @ResponseBody
    ResponseData getOrderDetails(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String orderId = request.getParameter("orderid");
        try {
            OrderEntity order = orderRepository.get(orderId);
            ProductEntity product = productRepository.get(order.getProductId());
            String orderState = null;
            switch (order.getState()) {
                case 1:
                    orderState = "待付款";
                    break;
                case 2:
                    orderState = "待发货";
                    break;
                case 3:
                    orderState = "待收货";
                    break;
                case 4:
                    orderState = "待评价";
                    break;
                case 5:
                    orderState = "已评价";
                    break;
            }
            String sendName = order.getSendName();
            String sendAddress = order.getSendAddress();
            String sendTel = order.getSendTel();
            String productImg = CommonMessage.IMG_URL + product.getProductFistImg();
            String productTitle = product.getProductName();
            String productPrice = String.valueOf(product.getPrice());
            String sums = String.valueOf(order.getSums());
            String typeSpecification = order.getTypeSpecification();
            String sumPrice = String.valueOf(order.getTotalPrice());
            String carriagePrice = String.valueOf(order.getCarriagePrice());
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String payTime = sdf.format(order.getPayTime());
            String deliverTime = sdf.format(order.getDeliverTime());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderState", orderState);
            jsonObject.put("sendName", sendName);
            jsonObject.put("sendAddress", sendAddress);
            jsonObject.put("sendTel", sendTel);
            jsonObject.put("productImg", productImg);
            jsonObject.put("productTitle", productTitle);
            jsonObject.put("productPrice", productPrice);
            jsonObject.put("sums", sums);
            jsonObject.put("typeSpecification", typeSpecification);
            jsonObject.put("sumPrice", sumPrice);
            jsonObject.put("carriagePrice", carriagePrice);
            jsonObject.put("payTime", payTime);
            jsonObject.put("deliverTime", deliverTime);
            JSONObject j[] = new JSONObject[1];
            j[0] = jsonObject;
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("orderDetails", j);
            responseData.setData(jsonObject1);
        } catch (Exception e) {
            logger.error(e, e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_ORDER_DETAILS_FAILED);
        } finally {
            return responseData;
        }
    }


    /**
     * 关键词查询商家
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/merchantsearch", method = RequestMethod.GET)
    public @ResponseBody ResponseData queryMerchants(HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        String key = request.getParameter("key");
        try {
            List<MerchantEntity> merchants = merchantRepository.search(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("merchants", merchants);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_GROUP_LIST_FAILED);
        } finally {
            return responseData;
        }
    }


    /**
     * 获取商家详细信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/merchantgetdetail", method = RequestMethod.GET)
    public @ResponseBody
    ResponseData getMerchant(HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        String merchantid = request.getParameter("merchantid");
        try {
            MerchantEntity merchant = merchantRepository.get(merchantid);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("merchant", merchant);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_GROUP_LIST_FAILED);
        } finally {
            return responseData;
        }
    }


    /**
     * 获取所有商家
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/merchantlistall", method = RequestMethod.GET)
    public @ResponseBody
    ResponseData getAllMerchants(HttpServletRequest request, HttpServletResponse response) {
        ResponseData responseData = new ResponseData();
        try {
            List<MerchantEntity> merchants = merchantRepository.findAll();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("merchants", merchants);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_GROUP_LIST_FAILED);
        } finally {
            return responseData;
        }
    }


    /**
     * 关键词查询商品
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/productsearch", method = RequestMethod.GET)
    public @ResponseBody
    ResponseData queryProducts(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String key = request.getParameter("key");
        try {
            List<ProductEntity> products = productRepository.search(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("products", products);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_GROUP_LIST_FAILED);
        } finally {
            return responseData;
        }
    }


    //获取商品详情
    @RequestMapping(value = "/getproductinfo", method = RequestMethod.GET)
    public @ResponseBody
    ResponseData getProductDetails(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String productId = request.getParameter("productid");
        try {
            JSONObject jsonObject = new JSONObject();
            ProductEntity product = productRepository.get(productId);
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String onlineTime = sdf.format(product.getOnlineTime());
            product.setProductFistImg(CommonMessage.IMG_URL + product.getProductFistImg());
            product.setImagesAddress(CommonMessage.IMG_URL + product.getImagesAddress());
            product.setProductSlideImg(CommonMessage.IMG_URL + product.getProductSlideImg());

            JSONObject jsonObject1 = new JSONObject();
            jsonObject1 = DisposeUtil.dispose(product);
            jsonObject1.put("onlineTime", onlineTime);

            jsonObject.put("productdetails", jsonObject1);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_PRODUCT_DETAILS_FAILED);
        } finally {
            return responseData;
        }
    }


        /**
         * 获取所有商品
         * @return
         */
        @RequestMapping(value = "/productlistall", method = RequestMethod.GET)
        public @ResponseBody ResponseData getAllProducts() {
            ResponseData responseData = new ResponseData();
            try {
                List<ProductEntity> products = productRepository.findAll();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("products", products);
                responseData.setData(jsonObject);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.GET_GROUP_LIST_FAILED);
            } finally {
                return responseData;
            }
        }


    /**
     * 获取所有用户
     * @return
     */
    @RequestMapping(value="/userlistall",method=RequestMethod.GET)
    public @ResponseBody ResponseData getAllUsers(){
        ResponseData responseData = new ResponseData();
        try {
            List<UserEntity> users = userRepository.findAll();
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",users);
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
     * 关键词查询用户
     * @param request
     * @return
     */
    @RequestMapping(value="/usersearch",method=RequestMethod.GET)
    public @ResponseBody ResponseData queryUsers(HttpServletRequest request){
        ResponseData responseData = new ResponseData();
        String key = request.getParameter("key");
        try {
            List<UserEntity> users = userRepository.search(key);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",users);
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
     * 获取用户详细信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/usergetdetail",method=RequestMethod.GET)
    public @ResponseBody ResponseData getuser(HttpServletRequest request, HttpServletResponse response){
        ResponseData responseData = new ResponseData();
        String usersid = request.getParameter("userid");
        try {
            UserEntity user= userRepository.get(usersid);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("user",user);
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
     * 获取所有快递
     * @param request
     * @param response
     * @return
     */

    @RequestMapping(value="/expressagelistall",method=RequestMethod.GET)
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
    @RequestMapping(value="/expressagesearch",method=RequestMethod.GET)
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



    /**
     * 获取快递详情
     * @param request
     * @return
     */

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





    //快递管理通过编辑存入需要的信息，并在前台展示
    @RequestMapping(value="/storeinformation",method = RequestMethod.GET)
    public @ResponseBody  ResponseData doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException,IOException{
        ResponseData responseData = new ResponseData();                         //不是修改，信息并没有锁定，而是增加了信息
        try {
            String expressageIsnew = request.getParameter("expressageIsnew");
            Integer isnew = Integer.parseInt(expressageIsnew);
            ExpressageEntity expressageEntity = new ExpressageEntity();
            expressageEntity.setIsNew(isnew);            //把页面的值创建一个
            String uuid = UUID.randomUUID().toString().replace("-", "");
            expressageEntity.setId(uuid);

            String expresageName=request.getParameter("expresageName");
            expressageEntity.setExpressageName(expresageName);

            String expresage_shipAddress=request.getParameter("expresage_shipAddress");
            expressageEntity.setShipAddress(expresage_shipAddress);
            expressageRepository.save(expressageEntity);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("result",true);
            responseData.setData(jsonObject);
        }
        catch (Exception e){
            responseData.setFailed(true);
            responseData.setFailedMessage("保存快递信息失败。");
        }
        finally {
            return responseData;
        }
    }






    /**
     * 创建管理员
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value="/authority/addmanage",method=RequestMethod.GET)
    public @ResponseBody ResponseData addmanage(HttpServletRequest request, HttpServletResponse response){
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










    //管理员权限设定（增加管理员权限）
    @RequestMapping(value="/authority/setmanageauth",method=RequestMethod.GET)
    public @ResponseBody ResponseData setmanageauth(HttpServletRequest request, HttpServletResponse response){
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








    //删除管理员


    @RequestMapping(value="/authority/deletemanage",method=RequestMethod.GET)
    public @ResponseBody ResponseData deletemanage(HttpServletRequest request, HttpServletResponse response){
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










}
