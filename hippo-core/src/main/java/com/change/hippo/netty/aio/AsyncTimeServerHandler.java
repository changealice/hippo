package com.change.hippo.netty.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/19
 * Time: 下午4:04
 */
public class AsyncTimeServerHandler implements Runnable {
    AsynchronousServerSocketChannel asynchronousServerSocketChannel;
    private AsynchronousChannelGroup asynchronousChannelGroup;
    CountDownLatch latch;

    public AsyncTimeServerHandler(int port) {
        try {
            asynchronousChannelGroup = AsynchronousChannelGroup.withThreadPool(Executors.newFixedThreadPool(4));
            asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);
            asynchronousServerSocketChannel.bind(new InetSocketAddress(port));

            System.out.println("The async time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        latch = new CountDownLatch(1);
        doAccept();
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doAccept() {
        asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
    }
}
