package com.iflytek.staff.chao.activemq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by chaowang5 on 2017/12/29.
 */
@Component
public class OrderCancelConsumer {
    Logger logger = LoggerFactory.getLogger(OrderCancelProducer.class);

    @JmsListener(destination = QueueConfig.ORDER_QUEUE_NAME)
    public void receivedQueue(String msg) {
        logger.info("received message :" + msg);
    }
}
