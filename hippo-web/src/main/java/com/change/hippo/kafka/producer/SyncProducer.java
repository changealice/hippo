package com.change.hippo.kafka.producer;

import java.util.Properties;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

/**
 * User: change.long
 * Date: 16/3/29
 * Time: 下午10:50
 * 同步生产kafka消息类
 */
public class SyncProducer {


    public static void main(String[] args) {

        String topic = "change";
        Properties props = new Properties();
        props.put("metadata.broker.list", "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("partitioner.class", "kafka.producer.DefaultPartitioner");
        //kafka.serializer.DefaultEncoder
        //kafka.producer.DefaultPartitioner: based on the hash of the key
        props.put("request.required.acks", "1");
        props.put("producer.type", "sync");
        //0;  绝不等确认  1:   leader的一个副本收到这条消息，并发回确认 -1：   leader的所有副本都收到这条消息，并发回确认
        ProducerConfig config = new ProducerConfig(props);

        Producer<String, String> producer = new Producer<String, String>(config);

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            KeyedMessage<String, String> message = new KeyedMessage<String, String>(topic,
                    Integer.toString(i), Integer.toString(i));
            producer.send(message);
        }


        producer.close();

    }


}
