package com.change.hippo.jms;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User: change.long
 * Date: 16/6/30
 * Time: 上午11:39
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = JmsApplication.class)
public class StartUpJmsApplicationForSpringJunit {

    @Autowired
    MessageSender messageSender;

    @Test
    public void test_send_product() throws Exception {
        StartUpJmsApplication.instantAndSend(messageSender);
    }
}
