package com.change.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/9
 * Time: 下午2:09
 */
public class KafkaMessageListener {
    private static final Logger logger = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "uap_account")
    public void listen1(String message) {
        logger.info("thread=" + Thread.currentThread().getName() + ",received: " + message);
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
