package com.change.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/17
 * Time: 上午12:40
 */
@Component
public class Receiver {

    private static final Logger LOGGER = LoggerFactory.getLogger(Receiver.class);

    @RabbitListener(queues = "my-queue")
    public void receiveMessage(String message) {
        LOGGER.info("收到rabbit消息{}", message);
    }
}
