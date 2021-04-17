package com.iflytek.staff.chao.activemq;

import org.apache.activemq.command.ActiveMQMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * Created by chaowang5 on 2017/12/29.
 */
@Component
public class TopicConsumer {
    Logger logger = LoggerFactory.getLogger(TopicConsumer.class);

    @JmsListener(destination = QueueConfig.ORDER_TOPIC_NAME, containerFactory = "jmsListenerContainerTopic")
    public void receivedTopic(ActiveMQMessage msg) {
        logger.info("received message topic :" + msg);
    }

    @JmsListener(destination = QueueConfig.ORDER_TOPIC_NAME, containerFactory = "jmsListenerContainerTopic")
    public void receivedTopic2(ActiveMQMessage msg) {
        logger.info("received message topic 2:" + msg.getMarshalledProperties().toString());

    }

    @JmsListener(destination = QueueConfig.ORDER_TOPIC_NAME, containerFactory = "jmsListenerContainerTopic")
    public void receivedTopic3(ActiveMQMessage msg) {
        logger.info("received message topic 3:" + msg.getMarshalledProperties());

    }
}
