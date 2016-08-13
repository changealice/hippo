package com.change.hippo.rocketmq;

import com.alibaba.rocketmq.client.consumer.DefaultMQPushConsumer;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import com.alibaba.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import com.alibaba.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * User: change.long
 * Date: 16/6/22
 * Time: 下午2:49
 * rocket mq consumer
 */
public class PushConsumer {
    private final static String ROCKET_MQ_INSTANCE_NAME = "Consumer";
    private final static String ROCKET_MQ_GROUP_NAME = "PushGroupName";
    private final static String ROCKET_MQ_SRV_ADDRESS = "127.0.0.1:9876";
    private final static String ROCKET_MQ_TOPIC = "change";

    public static void main(String[] args) throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(ROCKET_MQ_GROUP_NAME);

        consumer.setNamesrvAddr(ROCKET_MQ_SRV_ADDRESS);
        consumer.setInstanceName(ROCKET_MQ_INSTANCE_NAME);

        consumer.subscribe(ROCKET_MQ_TOPIC, null);
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs, ConsumeConcurrentlyContext context) {
                System.out.println(Thread.currentThread().getName()
                        + " Receive New Messages Size: " + msgs.size());
                MessageExt messageExt = msgs.get(0);
                if (messageExt.getTopic().equals(ROCKET_MQ_TOPIC)) {
                    System.out.println(new String(messageExt.getBody()));
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
        System.out.println("Consumer Started.");
    }

}
