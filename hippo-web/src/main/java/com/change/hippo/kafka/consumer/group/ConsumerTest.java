package com.change.hippo.kafka.consumer.group;

import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

/**
 * User: change.long
 * Date: 16/3/29
 * Time: 下午11:48
 */
public class ConsumerTest implements Runnable {


    private final KafkaStream stream;
    private final int threadNumber;

    public ConsumerTest(KafkaStream stream, int threadNumber) {
        this.stream = stream;
        this.threadNumber = threadNumber;
    }

    public void run() {
        ConsumerIterator<byte[], byte[]> it = stream.iterator();
        while (it.hasNext()) {
            System.out.println("Thread " + threadNumber + ": " + new String(it.next().message()));

        }
        System.out.println("Shutting down Thread: " + threadNumber);
    }
}
