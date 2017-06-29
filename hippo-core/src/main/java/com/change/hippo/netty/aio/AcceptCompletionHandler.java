package com.change.hippo.netty.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/19
 * Time: 下午4:08
 */
public class AcceptCompletionHandler implements
        CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {
    @Override
    public void completed(AsynchronousSocketChannel channel, AsyncTimeServerHandler handler) {

        //如果有新的客户端接入，系统会回调completion handler的completed方法。
        // 一个asynchronousServerSocketChannel可以连接成千上万个客户端，所以需要调用accept方法
        handler.asynchronousServerSocketChannel.accept(handler, this);


        ByteBuffer buffer = ByteBuffer.allocate(1024);
        channel.read(buffer, buffer, new ReadCompletionHandler(channel));
    }

    @Override
    public void failed(Throwable exc, AsyncTimeServerHandler handler) {
        exc.printStackTrace();
        handler.latch.countDown();
    }
}
