package com.change.hippo.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * User: change.long
 * Date: 16/6/30
 * Time: 上午11:17
 */
@Component
@Slf4j
public class MessageSender {


    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(Serializable message) {
        jmsTemplate.send(session -> session.createObjectMessage(message));
    }
}
