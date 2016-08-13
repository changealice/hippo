package com.change.hippo.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * User: change.long
 * Date: 16/3/25
 * Time: 下午3:20
 */
public class NativeProducer {

    public static void main(String[] args) throws InterruptedException {
        final String kafkaHostPort = "192.168.47.196:9092";
        final String topic = "uapaccount83";
        send(kafkaHostPort, topic);
    }

    private static void send(String kafkaHostPort, String topic) throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", kafkaHostPort);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = null;
        final Random random = new Random(1);
        try {
            producer = new KafkaProducer<>(props);
            while (true) {
                Future<RecordMetadata> future = producer.send(new ProducerRecord<>
                        (topic, "key:", "value:" + random.nextInt()));
                RecordMetadata recordMetadata = future.get();
                System.out.println(recordMetadata.topic() + " " + recordMetadata.offset() + " " + recordMetadata.partition());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
            producer.close();
        } finally {
            if (null != producer) {
                producer.close();
            }
        }
    }

}
