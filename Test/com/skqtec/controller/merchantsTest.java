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


    /*static final int SEND_NUMBER = 5;
    @org.junit.Test
    public static void main(String[] args) {
        // ConnectionFactory ：连接工厂，JMS 用它创建连接
        ConnectionFactory connectionFactory;
        // Connection ：JMS 客户端到JMS Provider 的连接
        Connection connection = null;
        // Session： 一个发送或接收消息的线程
        Session session;
        // Destination ：消息的目的地;消息发送给谁.
        Destination destination;
        // MessageProducer：消息发送者
        MessageProducer producer;
        // TextMessage message;
        // 构造ConnectionFactory实例对象，此处采用ActiveMq的实现jar
        connectionFactory = new ActiveMQConnectionFactory(
                "admin",
                "admin",
                "failover://tcp://121.196.202.96:61616");
        try {
            // 构造从工厂得到连接对象
            connection = connectionFactory.createConnection();
            // 启动
            connection.start();
            // 获取操作连接
            session = connection.createSession(Boolean.TRUE,
                    Session.AUTO_ACKNOWLEDGE);
            // 获取session注意参数值xingbo.xu-queue是一个服务器的queue，须在在ActiveMq的console配置
            destination = session.createQueue("FirstQueue");
            // 得到消息生成者【发送者】
            producer = session.createProducer(destination);
            // 设置不持久化，此处学习，实际根据项目决定
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            // 构造消息，此处写死，项目就是参数，或者方法获取
            sendMessage(session, producer);
            session.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != connection)
                    connection.close();
            } catch (Throwable ignore) {
            }
        }
    }

    public static void sendMessage(Session session, MessageProducer producer)
            throws Exception {
        for (int i = 1; i <= SEND_NUMBER; i++) {
            javax.jms.TextMessage message = session.createTextMessage("ActiveMq 发送的消息" + i);
            // 发送消息到目的地方
            System.out.println("发送消息：" + "ActiveMq 发送的消息" + i);
            producer.send(message);
        }
    }
*/
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