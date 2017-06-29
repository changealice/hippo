package com.change.hippo.netty.aio;

import com.change.hippo.util.SafeDateFormat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/19
 * Time: 下午4:18
 */
public class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {
    private AsynchronousSocketChannel channel;

    public ReadCompletionHandler(AsynchronousSocketChannel result) {
        if (channel == null) {
            this.channel = result;
        }

    }

    @Override
    public void completed(Integer result, ByteBuffer attachment) {
        attachment.flip();

        byte[] body = new byte[attachment.remaining()];
        attachment.get(body);

        try {
            String req = new String(body, "UTF-8");
            System.out.println("The time server receive order : " + req);
            boolean flag = "change.long".equalsIgnoreCase(req);
            String currentTime = flag ? SafeDateFormat.get().format(new Date()) : "BAD ORDER";
            doWrite(currentTime);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }

    private void doWrite(String response) {
        if (null != response && !"".equals(response)) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer, writeBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    if (buffer.hasRemaining()) {
                        channel.write(buffer, buffer, this);
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();

                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void failed(Throwable exc, ByteBuffer attachment) {
        exc.printStackTrace();
        try {
            channel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
