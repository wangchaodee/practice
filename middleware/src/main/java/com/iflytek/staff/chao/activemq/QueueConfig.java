package com.iflytek.staff.chao.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * Created by chaowang5 on 2017/12/29.
 */
@Configuration
public class QueueConfig {

    public static final String ORDER_QUEUE_NAME = "queue_11111";
    public static final String ORDER_TOPIC_NAME = "ActiveMQ.Advisory.Producer.Queue." + ORDER_QUEUE_NAME;

    @Bean
    public Queue orderQueue() {
        return new ActiveMQQueue(ORDER_QUEUE_NAME);
    }

    @Bean
    public Topic topic() {
        return new ActiveMQTopic(ORDER_TOPIC_NAME);
    }

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory("admin", "123456", "tcp://172.16.154.27:61616");
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerTopic(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setPubSubDomain(true);
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
        bean.setConnectionFactory(connectionFactory);
        return bean;
    }

    @Bean
    public JmsMessagingTemplate jmsMessagingTemplate(ActiveMQConnectionFactory connectionFactory) {
        return new JmsMessagingTemplate(connectionFactory);
    }
}
