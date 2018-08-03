package com.skqtec.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.UserEntity;
import com.skqtec.repository.SendAddressRepository;
import com.skqtec.repository.UserRepository;
import com.skqtec.tools.SessionTools;
import com.skqtec.tools.StoreTools;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Controller
@RequestMapping("/applet/users")
public class users {

    static Logger logger = Logger.getLogger(users.class.getName());

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SendAddressRepository sendAddressRepository;
    /**
     * 获取所有用户
     * @return
     */
    @RequestMapping(value="/listall",method=RequestMethod.GET)
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
    @RequestMapping(value="/search",method=RequestMethod.GET)
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
    @RequestMapping(value="/getdetail",method=RequestMethod.GET)
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
    //获取用户账户等级
    @RequestMapping(value="/getusergrade",method=RequestMethod.GET)
    public @ResponseBody ResponseData getUserGrade(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String userId = request.getParameter("userid");
        try {
            UserEntity user = userRepository.get(userId);
            String userGrade = String.valueOf(user.getGrade());
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("userGrade", userGrade);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_USER_GRADE_FAILED);
        } finally {
            return responseData;
        }
    }
    //获取children信息
    @RequestMapping(value="/getchildren",method=RequestMethod.GET)
    public @ResponseBody ResponseData getChildren(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String userId=request.getParameter("userid");
        try {
            UserEntity user=userRepository.get(userId);
            String childrens=user.getChildren();
            JSONArray jChildrens = JSONArray.parseArray(childrens);
            List<JSONObject>jsonObject1=new ArrayList<JSONObject>();
            for(int i=0;i<jChildrens.size();i++){
                String childId=jChildrens.getString(i);
                UserEntity child=userRepository.get(childId);
                String nickName=child.getNickname();
                String headImg=CommonMessage.IMG_URL+child.getHeadImgUrl();
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("nickName",nickName);
                jsonObject.put("headImg",headImg);
                jsonObject1.add(jsonObject);
            }
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("childrenInfo",jsonObject1);
            responseData.setData(jsonObject);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.GET_CHILDREN_FAILED);
        } finally {
            return responseData;
        }
    }

    //绑定手机号-发送验证码
    @RequestMapping(value="/sendverficationcode",method=RequestMethod.GET)
    public @ResponseBody ResponseData sendVerficationCode(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String phone = request.getParameter("phone");
        String sessionId=request.getParameter("sessionid");
        try {
            //判断是否登录
           /* String userId=SessionTools.sessionQuery(sessionId);
            if(userId==null){
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
                return responseData;
            }*/
            //生成六位随机数
            String base = "0123456789";
            Random random = new Random();
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 6; i++) {
                int number = random.nextInt(base.length());
                sb.append(base.charAt(number));
            }
            String verficationCode=sb.toString();
            StoreTools.storeInfo(sessionId+"0",verficationCode,900);
            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod("http://sms.webchinese.cn/web_api/");
            post.addRequestHeader("Content-Type",
                    "application/x-www-form-urlencoded;charset=gbk");// 在头文件中设置转码
            NameValuePair[] data = {new NameValuePair("Uid", "lijie1108"), // 注册的用户名
                    new NameValuePair("Key", "d41d8cd98f00b204e980"), // 注册成功后,登录网站使用的密钥
                    new NameValuePair("smsMob", phone), // 手机号码
                    new NameValuePair("smsText", "您的验证码是"+verficationCode+"，在十五分钟内有效。如非本人操作请忽略本短信。")};//设置短信内容
            post.setRequestBody(data);
            client.executeMethod(post);
            Header[] headers = post.getResponseHeaders();
            int statusCode = post.getStatusCode();
            System.out.println("statusCode:" + statusCode);
            for (Header h : headers) {
                System.out.println(h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes(
                    "gbk"));
            System.out.println(result);
            post.releaseConnection();

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.SEND_VERFICATION_CODE_FAILED);
        } finally {
            return responseData;
        }
    }

    //绑定手机号-验证验证码
    @RequestMapping(value="/checkverficationcode",method=RequestMethod.GET)
    public @ResponseBody ResponseData checkVerficationCode(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        String verficationCode=request.getParameter("verficationcode");
        String sessionId=request.getParameter("sessionid");
        try{
            //判断是否登录
           /* String userId=SessionTools.sessionQuery(sessionId);
            if(userId==null){
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.NOT_LOG_IN);
                return responseData;
            }*/
            if(!verficationCode.equals(StoreTools.getInfo(sessionId+"0"))) {
                responseData.setFailed(true);
                responseData.setFailedMessage(CommonMessage.CHECK_VERFICATION_CODE_FAILED);
            }
        }catch(Exception e){
            logger.error(e.getMessage(), e);
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.CHECK_VERFICATION_CODE_FAILED);
        }finally{
            return responseData;
        }
    }

    //用户登录
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public @ResponseBody ResponseData login(HttpServletRequest request) {
        ResponseData responseData = new ResponseData();
        try {
            String code=request.getParameter("code");
            //WXUtils wxUtils = new WXUtils();
            String rawData=request.getParameter("rawData");
            String signature=request.getParameter("signature");
            //String encryptedData=request.getParameter("encryptedData");
            //String iv=request.getParameter("iv");
            String APPID="wx5733cafea467c980";
            String APPSECRET="5c0e7f53469730421e2d7a5e9464df4f";
            String url = "https://api.weixin.qq.com/sns/jscode2session?appid="+APPID+"&secret="+APPSECRET+"&js_code="+code+"&grant_type=authorization_code";
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
            if(responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK)
            {
                String sessionData = responseEntity.getBody();
                logger.info("sessionData = "+ sessionData);
                JSONObject jsonObj = JSON.parseObject(sessionData);
                String openId = jsonObj.getString("openid");
                String sessionKey = jsonObj.getString("session_key");
                UserEntity user=userRepository.query(openId);
                if(user==null){
                    //创建用户
                    user=new UserEntity();
                    String uuid = UUID.randomUUID().toString().replace("-", "");
                    user.setId(uuid);
                    user.setOpenid(openId);
                }
                String signature1 = DigestUtils.shaHex(rawData+sessionKey);
                if(!signature1.equals(signature)) //验证签名
                {
                    logger.info(" req signature="+signature);
                    logger.info(" java signature="+signature);
                    responseData.setFailed(true);
                    responseData.setFailedMessage(CommonMessage.LOGIN_FAILED);
                }
                JSONObject resultJson =JSON.parseObject(rawData);
                //resultJson = WXUtils.getUserInfo(encryptedData,sessionKey,iv);
                user.setNickname(resultJson.getString("nickName"));
                user.setSex(resultJson.getIntValue("gender"));
                user.setHeadImgUrl(resultJson.getString("avatarUrl"));
                //userPo.setLoginId(userInfoObj.getString("unionId"));
                user.setCity(resultJson.getString("city"));
                user.setCountry(resultJson.getString("country"));
                user.setProvince(resultJson.getString("province"));
                userRepository.saveOrUpdate(user);
                String sessionId=SessionTools.setSession(user.getId());
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("sessionId",sessionId);
                responseData.setData(jsonObject);
                }else
                {
                    responseData.setFailed(true);
                    responseData.setFailedMessage(CommonMessage.LOGIN_FAILED);
                }
        } catch (Exception e) {
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.LOGIN_FAILED);
        } finally {
            return responseData;
        }
    }
}
