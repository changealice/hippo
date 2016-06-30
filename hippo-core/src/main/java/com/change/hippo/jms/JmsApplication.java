package com.change.hippo.jms;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * User: change.long
 * Date: 16/6/30
 * Time: 上午11:38
 */
@Configuration
@ComponentScan(basePackages = "com.change.hippo.jms")
@Import({ActiveMQJmsConfiguration.class, JmsListenerConfiguration.class})
public class JmsApplication {


}
