package com.change.runnner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.concurrent.TimeUnit;

/**
 * User: change.long@99bill.com
 * Date: 2016/12/17
 * Time: 上午12:37
 */
public class RabbitMQCommandLineRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQCommandLineRunner.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Bean
    public Queue myQueue() {
        return new Queue("my-queue");
    }

    @Override
    public void run(String... args) throws Exception {

        for (int i = 0; i < 1; i++) {
            InetAddress localHost = Inet4Address.getLocalHost();
            try {
                rabbitTemplate.convertAndSend("my-queue", "来之rabbit MQ问候" + i + localHost);
            } catch (AmqpException e) {
                logger.error(e.getMessage(),e);
            }
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
