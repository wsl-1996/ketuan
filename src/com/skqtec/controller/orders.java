package com.skqtec.controller;

import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.*;
import com.skqtec.repository.*;
import com.skqtec.tools.SessionTools;
import com.skqtec.wxtools.WXPayConstants;
import com.skqtec.wxtools.WXPayUtil;
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
import java.util.*;

@Controller
@RequestMapping("/applet/orders")
public class orders {
    static Logger logger = Logger.getLogger(orders.class.getName());

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SendAddressRepository sendAddressRepository;
    //生成订单
    @RequestMapping(value="/createorder",method = RequestMethod.GET)
    public @ResponseBody ResponseData createOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseData responseData=new ResponseData();
        String deduction=request.getParameter("deduction");//抵扣金额
        String productId=request.getParameter("productid");
        String groupId=request.getParameter("groupid");
        String sessionId=request.getParameter("sessionid");
        String totalPrice=request.getParameter("totalprice");
        String productStyle=request.getParameter("style");
        String meno=request.getParameter("meno");
        String productPrice=request.getParameter("productprice");
        int sums=Integer.parseInt(request.getParameter("sums"));
        int carriagePrice=Integer.parseInt(request.getParameter("carriageprice"));
        //判断是否登录
        String userId=SessionTools.sessionQuery(sessionId);
        if(userId==null){
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
            return responseData;
        }
        UserEntity user=userRepository.get(userId);
        String openId=user.getOpenid();
        String fdAddress=user.getFirstDeliverAddress();
        if(fdAddress==null){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("error","未填收货地址");
            responseData.setData(jsonObject);
        }
        //String paymethod=request.getParameter("");
       // String uuid = UUID.randomUUID().toString().replace("-", "");
        String out_trade_no=WXPayUtil.getOrderNo();
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setCarriagePrice(carriagePrice);
        orderEntity.setGroupId(groupId);
        orderEntity.setId(out_trade_no);
        orderEntity.setMeno(meno);
        orderEntity.setOrderTime( new Timestamp(System.currentTimeMillis()));
        orderEntity.setProductId(productId);
        orderEntity.setProductPrice(Integer.parseInt(productPrice));
        ProductEntity product=productRepository.get(productId);
        SendaddressEntity sendaddress=sendAddressRepository.get(fdAddress);
        orderEntity.setSendAddress(sendaddress.getId());
        orderEntity.setSendName(sendaddress.getSendName());
        orderEntity.setSendTel(sendaddress.getSendPhone());
        orderEntity.setSendZip(sendaddress.getZip());
        orderEntity.setState(1);
        orderEntity.setTotalPrice(Integer.parseInt(totalPrice));
        orderEntity.setUserId(userId);
        orderEntity.setPaymethod("微信支付");
        orderEntity.setTypeSpecification(productStyle);
        orderEntity.setSums(sums);
        orderEntity.setDeduction(Double.parseDouble(deduction));
        orderEntity.setDescript(product.getProductName()+product.getEvaluateLabel()+product.getProductInfo()+product.getProductLabel());
        try{
            logger.info("********product save returned :  "+orderRepository.save(orderEntity));
            //支付
            JSONObject j=null;
            String payMoney=String.valueOf((int)((Double.parseDouble(totalPrice)-Double.parseDouble(deduction))*100));
            System.out.println(payMoney);
            j=payment.payRequest(out_trade_no,productId,openId,payMoney);
            Map<String, String> data=(Map)j.get("data");
            String timeStamp=String.valueOf(new Date().getTime()/1000);
            String nonceStr=data.get("nonce_str");
            String package1="prepay_id="+data.get("prepay_id");
            String signType="MD5";
            Map<String,String>reqData=new HashMap<String, String>();
            reqData.put("appId","wx5733cafea467c980");
            reqData.put("nonceStr",nonceStr);
            reqData.put("package",package1);
            reqData.put("signType",signType);
            reqData.put("timeStamp",timeStamp);
            String paySign=WXPayUtil.generateSignature(reqData,"lijie1108NANCYlijie1108skqtec01s",WXPayConstants.SignType.MD5);
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("timeStamp",timeStamp);
            jsonObject.put("nonceStr",nonceStr);
            jsonObject.put("package",package1);
            jsonObject.put("signType",signType);
            jsonObject.put("paySign",paySign);
            responseData.setData(jsonObject);
        }
        catch (Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.CREATE_ORDER_FAILED);
        }
        finally {
            return responseData;
        }
    }
    //未支付订单支付接口
    @RequestMapping(value="/orderpay",method = RequestMethod.GET)
    public @ResponseBody ResponseData orderPay(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ResponseData responseData = new ResponseData();
        String orderId = request.getParameter("orderid");
        try {
            OrderEntity order = orderRepository.get(orderId);
            UserEntity user = userRepository.get(order.getUserId());
            double totalPrice = order.getTotalPrice();
            double deduction = order.getDeduction();
            String productId = order.getProductId();
            String openId = user.getOpenid();
            String payMoney = String.valueOf((int) ((totalPrice - deduction) * 100));
            String out_trade_no = orderId;
            JSONObject j = payment.payRequest(out_trade_no, productId, openId, payMoney);
            Map<String, String> data = (Map) j.get("data");
            String timeStamp = String.valueOf(new Date().getTime() / 1000);
            String nonceStr = data.get("nonce_str");
            String package1 = "prepay_id=" + data.get("prepay_id");
            String signType = "MD5";
            Map<String, String> reqData = new HashMap<String, String>();
            reqData.put("appId", "wx5733cafea467c980");
            reqData.put("nonceStr", nonceStr);
            reqData.put("package", package1);
            reqData.put("signType", signType);
            reqData.put("timeStamp", timeStamp);
            String paySign = WXPayUtil.generateSignature(reqData, "lijie1108NANCYlijie1108skqtec01s", WXPayConstants.SignType.MD5);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("timeStamp", timeStamp);
            jsonObject.put("nonceStr", nonceStr);
            jsonObject.put("package", package1);
            jsonObject.put("signType", signType);
            jsonObject.put("paySign", paySign);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e, e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.PAY_FAILED);
        } finally {
            return responseData;
        }
    }

    //获取订单
    @RequestMapping(value="/getorder",method = RequestMethod.GET)
    public @ResponseBody ResponseData getOrder(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        //String userId=request.getParameter("userid");
        String orderState=request.getParameter("orderstate");
        String sessionId=request.getParameter("sessionid");
        try{
            String userId=SessionTools.sessionQuery(sessionId);
            if(userId==null){
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
                return responseData;
            }

            List<OrderEntity>orders=new ArrayList<OrderEntity>();
            List<JSONObject>j=new ArrayList<JSONObject>();
            orders=orderRepository.query(userId,orderState);
            for(OrderEntity order:orders){
                ProductEntity product=productRepository.get(order.getProductId());
                String shopName=null;
                if(product.getOwnerType()==1) {
                    MerchantEntity merchant = merchantRepository.get(product.getMerchantId());
                    shopName=merchant.getName();
                }
                else{
                    UserEntity user=userRepository.get(product.getUserId());
                    shopName=user.getNickname();
                }
                String orderId=order.getId();
                String productImg=product.getProductFistImg();
                String productTitle=product.getProductName();
                String productPrice=String.valueOf(product.getPrice());
                String sums=String.valueOf(order.getSums());
                String typeSpecification=order.getTypeSpecification();
                String sumPrice=String.valueOf(order.getTotalPrice());
                String deduction=String.valueOf(order.getDeduction());
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("shopName",shopName);
                jsonObject.put("orderId",orderId);
                jsonObject.put("orderState",orderState);
                jsonObject.put("productImg",productImg);
                jsonObject.put("productTitle",productTitle);
                jsonObject.put("productPrice",productPrice);
                jsonObject.put("sums",sums);
                jsonObject.put("deduction",deduction);
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
            responseData.setFailedMessage(CommonMessage.GET_ORDER_FAILED);
        }finally{
            return responseData;
        }
    }
    //删除订单
    @RequestMapping(value="/removeorder",method = RequestMethod.GET)
    public @ResponseBody ResponseData removeOrder(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        String orderId=request.getParameter("orderid");
        String sessionId=request.getParameter("sessionid");
        try{
            String userId=SessionTools.sessionQuery(sessionId);
            if(userId==null){
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
                return responseData;
            }
            OrderEntity order=orderRepository.get(orderId);
            order.setState(-1);
            orderRepository.saveOrUpdate(order);
        }catch(Exception e){
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.REMOVE_ORDER_FAILED);
        }finally{
            return responseData;
        }
    }
    //订单搜索
    @RequestMapping(value="/searchorders",method = RequestMethod.GET)
    public @ResponseBody ResponseData searchOrder(HttpServletRequest request){
        ResponseData responseData=new ResponseData();
        //String userId=request.getParameter("userid");
        String key=request.getParameter("key");
        String sessionId=request.getParameter("sessionid");
        try{
            String userId=SessionTools.sessionQuery(sessionId);
            if(userId==null){
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
                return responseData;
            }
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
               /* switch(order.getState()){
                    case 1:orderState="待付款";break;
                    case 2:orderState="待发货";break;
                    case 3:orderState="待收货";break;
                    case 4:orderState="待评价";break;
                    case 5:orderState="已评价";break;
                }*/
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
    @RequestMapping(value="/getorderdetails",method = RequestMethod.GET)
    public @ResponseBody ResponseData getOrderDetails(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String orderId = request.getParameter("orderid");
        String sessionId=request.getParameter("sessionid");
        try {
            String userId=SessionTools.sessionQuery(sessionId);
            if(userId==null){
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
                return responseData;
            }
            OrderEntity order=orderRepository.get(orderId);
            ProductEntity product=productRepository.get(order.getProductId());
            String orderState=null;
            switch(order.getState()){
                case 0:orderState="已完成";break;
                case 1:orderState="待付款";break;
                case 2:orderState="待发货";break;
                case 3:orderState="待收货";break;
                case 4:orderState="待评价";break;
                //case 5:orderState="已评价";break;
            }
            String sendName=order.getSendName();
            SendaddressEntity sendaddressEntity=sendAddressRepository.get(order.getSendAddress());
            String sendAddress=sendaddressEntity.getProvince()+sendaddressEntity.getCity()+sendaddressEntity.getDistricts()+sendaddressEntity.getAddressDetail();
            String sendTel=order.getSendTel();
            String productImg=product.getProductFistImg();
            String productTitle=product.getProductName();
            String productPrice=String.valueOf(product.getPrice());
            String sums=String.valueOf(order.getSums());
            String typeSpecification=order.getTypeSpecification();
            String sumPrice=String.valueOf(order.getTotalPrice());
            String carriagePrice=String.valueOf(order.getCarriagePrice());
            DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            String payTime=null;
            if(order.getState()>=2||order.getState()==0)
                payTime=sdf.format(order.getPayTime());
            String deliverTime=null;
            if(order.getState()>=3||order.getState()==0)
                deliverTime=sdf.format(order.getDeliverTime());
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("orderState",orderState);
            jsonObject.put("sendName",sendName);
            jsonObject.put("sendAddress",sendAddress);
            jsonObject.put("sendTel",sendTel);
            jsonObject.put("productImg",productImg);
            jsonObject.put("productTitle",productTitle);
            jsonObject.put("productPrice",productPrice);
            jsonObject.put("sums",sums);
            jsonObject.put("typeSpecification",typeSpecification);
            jsonObject.put("sumPrice",sumPrice);
            jsonObject.put("carriagePrice",carriagePrice);
            jsonObject.put("payTime",payTime);
            jsonObject.put("deliverTime",deliverTime);
            JSONObject j[]=new JSONObject[1];
            j[0]=jsonObject;
            JSONObject jsonObject1=new JSONObject();
            jsonObject1.put("orderDetails",j);
            responseData.setData(jsonObject1);
        } catch (Exception e) {
            logger.error(e,e.fillInStackTrace());
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_ORDER_DETAILS_FAILED);
        } finally {
            return  responseData;
        }
    }
}