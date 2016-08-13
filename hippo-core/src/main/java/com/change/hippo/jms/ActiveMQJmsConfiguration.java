package com.change.hippo.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import java.util.Arrays;

/**
 * User: change.long
 * Date: 16/6/29
 * Time: 下午11:12
 */
@Configuration
public class ActiveMQJmsConfiguration {


    private static final String BROKER_URL = "tcp://127.0.0.1:61616";

    private static final String ORDER_QUEUE = "order-queue";

//    private static final String ORDER_RESPONSE_QUEUE = "order-queue";

//    @Autowired
//    private MessageReceiver messageReceiver;

    @Bean
    public ConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(BROKER_URL);
        connectionFactory.setTrustedPackages(Arrays.asList("com.change", "java.util"));
        connectionFactory.setUserName("change");
        connectionFactory.setPassword("change1988");
        return connectionFactory;
    }

    @Bean
    public ConnectionFactory cacheConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(connectionFactory());
        cachingConnectionFactory.setSessionCacheSize(20);
        return cachingConnectionFactory;
    }

//    @Bean
//    public MessageListenerContainer messageListenerContainer() {
//        DefaultMessageListenerContainer messageListenerContainer = new DefaultMessageListenerContainer();
//        messageListenerContainer.setConnectionFactory(connectionFactory());
//        messageListenerContainer.setDestinationName(ORDER_QUEUE);
//        messageListenerContainer.setMessageListener(messageReceiver);
//        return messageListenerContainer;
//    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate(cacheConnectionFactory());
        jmsTemplate.setDefaultDestinationName(ORDER_QUEUE);
        return jmsTemplate;
    }
//
//    @Bean
//    public MessageConverter messageConverter() {
//        return new SimpleMessageConverter();
//    }


}
