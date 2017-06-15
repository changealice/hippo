package com.change.hippo.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

import lombok.extern.slf4j.Slf4j;


/**
 * User: change.long
 * Date: 16/6/30
 * Time: 上午11:17
 */
@Component
@Slf4j
public class MessageReceiver2 {
    private static final String ORDER_QUEUE = "order-queue";

    @JmsListener(destination = MessageReceiver2.ORDER_QUEUE)
    public void receiveMessage(final Message<Product> message) throws JMSException {
        log.info("Application : header received : {}", message.getHeaders());
        log.info("Application : message received : {}", message.getPayload());
    }
}
