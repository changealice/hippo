package com.change.hippo.kafka;


import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * User: change.long
 * Date: 16/3/25
 * Time: 下午2:22
 */
public class NativeConsumer {

    private static final ThreadPoolExecutor executorService = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

    public static void main(String[] args) throws InterruptedException, IOException {
		ClassPathResource classPathResource = new ClassPathResource("log4j.properties");
		PropertyConfigurator.configure(classPathResource.getURL());

        final String kafkaHostPort = "192.168.47.196:9092";
//        final String kafkaHostPort = "10.199.204.23:9092,10.199.204.24:9092";
        final String topic = "testPartition_1";
        final String group1 = "group5";
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    consumer(kafkaHostPort, topic, group1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Consumer-starter").start();


    }

    private static void consumer(String kafkaHostPort, String topic, String group) throws InterruptedException {
		Properties props = getConsumerProperties(kafkaHostPort, group);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topic));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(200);
            if (records != null && records.count() > 0) {
                    try {
                        process(records);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            }
        }

    }

	private static Properties getConsumerProperties(String kafkaHostPort, String group) {
		Properties props = new Properties();
		props.put("bootstrap.servers", kafkaHostPort);
		props.put("group.id", group);
		props.put("enable.auto.commit", "true");
		props.put("auto.offset.reset", "earliest");
		props.put("auto.commit.interval.ms", "6000");
		props.put("connections.max.idle.ms", "600000");
		props.put("session.timeout.ms", "30000");
		props.put("request.timeout.ms", "600000");
		props.put("heartbeat.interval.ms", "15000");
		props.put("max.partition.fetch.bytes", "524288");//0.5kb
		props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		return props;
	}

	private static void process(ConsumerRecords<String, String> records) throws InterruptedException {
        for (ConsumerRecord<String, String> record : records) {
			System.err.printf("partition = %d ,offset = %d, key = %s, value = %s,count=%s\n",
					record.partition(), record.offset(), record.key(), record.value(), record);
		}
		Thread.sleep(35000);

    }


}
