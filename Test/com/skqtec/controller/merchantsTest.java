package com.skqtec.controller;

import java.util.HashMap;

public class merchantsTest {

    @org.junit.Before
    public void setUp() throws Exception {

    }



    @org.junit.Test
    public void main() {
        HashMap<String,String> s=new HashMap<String, String>();
        String str="ddd";
        s.put("ss",str);
        s.remove("ss");
        for (HashMap.Entry<String, String>entry: s.entrySet()){
            System.out.println(entry.getValue());
        }
    }

   /* public static void sendMessage(Session session, MessageProducer producer)
            throws Exception {
        for (int i = 1; i <= SEND_NUMBER; i++) {
            javax.jms.TextMessage message = session.createTextMessage("ActiveMq 发送的消息" + i);
            // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
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
            /*GroupEntity groupEntity=groupRepository.get("04");
            Timestamp endTime=groupEntity.getEndTime();
            ChangeGroupState.addToRedis("04",endTime);
        } catch (Exception e) {
            responseData.setFailed(true);
            responseData.setFailedMessage(CommonMessage.TEST_FAILED);
        } finally {

        }
    }
*/

}