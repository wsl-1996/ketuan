package com.skqtec.controller;

import com.skqtec.common.CommonMessage;
import com.skqtec.common.ResponseData;
import com.skqtec.entity.GroupEntity;
import com.skqtec.repository.GroupRepository;
import com.skqtec.timertask.ChangeGroupState;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public class merchantsTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void createProduct() {
        String a= "2018-07-20 23:00:00";
        String b="2018-07-20 22:59:00";
        Timestamp a1 = Timestamp.valueOf(a);
        Timestamp b1 = Timestamp.valueOf(b);
        long t1 = a1.getTime();
        long t2 = b1.getTime();
        long delta  = t1-t2;

        System.out.println(delta);
    }

    @Autowired
    private GroupRepository groupRepository;

    @org.junit.Test
    public void getAllMerchants() {
        ResponseData responseData = new ResponseData();
        try {
            /*Map<String,String> reqData=new HashMap<String, String>();
            reqData.put("appId","wx5733cafea467c980");
            reqData.put("nonceStr","fjVOtMdZ9f0ALW74");
            reqData.put("package","prepay_id=wx03174838573134b762d0256c3218565446");
            reqData.put("signType","MD5");
            reqData.put("timeStamp","1533289949");
            String paySign=WXPayUtil.generateSignature(reqData,"lijie1987110801spaceorg1234qwerl",WXPayConstants.SignType.MD5);
            System.out.println(paySign);*/

            /*int totalMoney=cashBackRepository.statisticCashback("01");
            System.out.println(totalMoney);*/
            GroupEntity groupEntity=groupRepository.get("04");
            Timestamp endTime=groupEntity.getEndTime();
            ChangeGroupState.addToRedis("04",endTime);
        } catch (Exception e) {
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.TEST_FAILED);
        } finally {

        }
    }

    @org.junit.Test
    public void queryMerchants() {
    }


}