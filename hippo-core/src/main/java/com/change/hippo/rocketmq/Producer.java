package com.change.hippo.rocketmq;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;

import java.util.concurrent.TimeUnit;

/**
 * User: change.long
 * Date: 16/6/22
 * Time: 下午2:36
 * rocket mq producer
 */
public class Producer {
    private final static String ROCKET_MQ_INSTANCE_NAME = "Producer";
    private final static String ROCKET_MQ_GROUP_NAME = "ProducerGroupName";
    private final static String ROCKET_MQ_SRV_ADDRESS = "127.0.0.1:9876";
    private final static String ROCKET_MQ_TOPIC = "change";
    private final static byte[] ROCKET_MQ_SEND_CONTENT_BYTES = "Hello MetaQ".getBytes();

    public static void main(String[] args) throws MQClientException {
        int concurrency = 10;
        Thread[] threads = new Thread[concurrency];
        for (int i = 0, len = concurrency; i < len; i++) {
            int j = i;
            Thread thread = new Thread(() -> {
                try {
                    send(j);
                } catch (MQClientException e) {
                    e.printStackTrace();
                }
            }, "Producer" + i);
            threads[i] = thread;
            thread.start();
        }
        for (int i = 0, len = threads.length; i < len; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException ignore) {
            }
        }

    }

    private static void send(int index) throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer(ROCKET_MQ_GROUP_NAME + index);
        producer.setNamesrvAddr(ROCKET_MQ_SRV_ADDRESS);
        producer.setInstanceName(ROCKET_MQ_INSTANCE_NAME);
        producer.start();
        //发送消息
        try {
            for (int i = 0, len = 100; i < len; i++) {
                SendResult sendResult = producer.send(new Message(ROCKET_MQ_TOPIC, ROCKET_MQ_SEND_CONTENT_BYTES));
                System.out.println(sendResult);
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            producer.shutdown();
        }
    }

}
