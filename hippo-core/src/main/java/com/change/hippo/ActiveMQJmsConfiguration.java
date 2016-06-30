package com.change.hippo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.ConnectionFactory;

/**
 * User: change.long
 * Date: 16/6/29
 * Time: 下午11:12
 */
@Configuration
public class ActiveMQJmsConfiguration {


    private static final String ACTIVE_MQ_ADDRESS = "tcp://localhost:61616";

    private static final String ORDER_QUEUE = "order-queue";

    private static final String ORDER_RESPONSE_QUEUE = "order-response-queue";

    @Bean
    public ConnectionFactory connectionFactory() {
        return null;
    }
}
