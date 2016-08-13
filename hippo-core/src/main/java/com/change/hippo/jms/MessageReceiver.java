package com.change.hippo.jms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.support.converter.MessageConverter;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

/**
 * User: change.long
 * Date: 16/6/30
 * Time: 上午11:17
 */
//@Component
@Slf4j
public class MessageReceiver implements MessageListener {


    //    @Autowired
    private MessageConverter messageConverter;

    @Override
    public void onMessage(Message message) {
        try {
            Object messageToUse = messageConverter.fromMessage(message);
            log.info("Application : message received : {}", messageToUse);
        } catch (JMSException e) {
            log.error(e.getMessage(), e);
        }
    }
}
