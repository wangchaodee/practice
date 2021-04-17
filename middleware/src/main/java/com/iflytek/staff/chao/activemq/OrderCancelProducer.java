package com.iflytek.staff.chao.activemq;


import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.ScheduledMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.*;


/**
 * Created by chaowang5 on 2017/12/29.
 */
@Slf4j
@Component
public class OrderCancelProducer implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(OrderCancelProducer.class);

    @Autowired
    private Queue orderQueue;

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Override
    public void run(String... args) throws Exception {
        send("order mess");
        logger.info("send message finished ");

    }

    public void send(String msg) {
        this.jmsMessagingTemplate.convertAndSend(orderQueue, msg);
    }

    public void delaySend(String msg, long time) {
        //获取连接工厂
        ConnectionFactory connectionFactory = this.jmsMessagingTemplate.getConnectionFactory();
        try {
            //获取连接
            Connection connection = connectionFactory.createConnection();
            connection.start();
            //获取session
            Session session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);
            // 创建一个消息队列
//            Destination destination = session.createQueue(queueName);
            MessageProducer producer = session.createProducer(orderQueue);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            TextMessage message = session.createTextMessage(msg);
            //设置延迟时间
            message.setLongProperty(ScheduledMessage.AMQ_SCHEDULED_DELAY, time);
            //发送
            producer.send(message);
            logger.info("send message : " + msg);
            session.commit();
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

}
