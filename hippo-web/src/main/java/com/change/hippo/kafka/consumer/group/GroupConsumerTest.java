package com.change.hippo.kafka.consumer.group;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * User: change.long
 * Date: 16/3/29
 * Time: 下午11:38
 */
public class GroupConsumerTest {


    private final String zookeeper;
    private final String topic;
    private final String group;
    ConsumerConnector consumerConnector;
    private ExecutorService executor;

    public GroupConsumerTest(String zookeeper, String topic, String group) {
        this.zookeeper = zookeeper;
        this.topic = topic;
        this.group = group;
        this.consumerConnector = Consumer.createJavaConsumerConnector(createConfig(zookeeper, topic, group));
    }

    public static void main(String[] args) {
        String zookeeper = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
        String topic = "change";
        String group = "change_group";
        GroupConsumerTest groupConsumerTest = new GroupConsumerTest(zookeeper, topic, group);
        groupConsumerTest.run(3);
        groupConsumerTest.shutdown();
    }

    private void shutdown() {


    }

    private void run(int a_numThreads) {
        Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
        topicCountMap.put(topic, a_numThreads);
        Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap);
        List<KafkaStream<byte[], byte[]>> stream = consumerMap.get(topic);
        // now launch all the threads
        //
        executor = Executors.newFixedThreadPool(a_numThreads);

        // now create an object to consume the messages
        //
        int threadNumber = 0;
        for (final KafkaStream kafkaStream : stream) {
            executor.submit(new ConsumerTest(kafkaStream, threadNumber));
            threadNumber++;
        }
    }

    private ConsumerConfig createConfig(String zookeeper, String topic, String group) {
        Properties props = new Properties();
        props.put("zookeeper.connect", zookeeper);
        props.put("group.id", group);
        props.put("zookeeper.session.timeout.ms", "40000");
        props.put("zookeeper.sync.time.ms", "2000");
        props.put("auto.commit.interval.ms", "1000");
        return new ConsumerConfig(props);
    }
}
