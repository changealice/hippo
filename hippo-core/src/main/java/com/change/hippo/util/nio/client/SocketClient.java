package com.change.hippo.util.nio.client;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/26
 * Time: 下午3:06
 */
public class SocketClient {

    public static void main(String[] args) throws Exception {
        testChannelSelector();
    }


    private static void testChannelSelector() throws Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 18080));

        while (!socketChannel.finishConnect()) {
            System.out.println("finishConnect false");
        }

        Selector selector = Selector.open();


        SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE, "attachment");

        while (true) {
            int readyChannels = selector.select();
            if (readyChannels == 0) {
                continue;
            }
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey selectionKey = keyIterator.next();
                if (selectionKey.isAcceptable()) {
                    System.out.println(selectionKeys + " isAcceptable");
                } else if (selectionKey.isConnectable()) {
                    System.out.println(selectionKeys + " isConnectable");
                } else if (selectionKey.isReadable()) {
                    System.out.println(selectionKeys + " isReadable");
                } else if (key.isWritable()) {
                    ByteBuffer byteBuffer = ByteBuffer.allocate(32);
                    for (int i = 0; i < 32; i++) {
                        byteBuffer.put((byte) i);
                    }
                    SocketChannel channel = (SocketChannel) selectionKey.channel();
                    channel.write(byteBuffer);
                    System.out.println(selectionKeys + " isWritable");
                } else {
                    System.out.println(selectionKeys + " not ready");
                }
                keyIterator.remove();
            }
            TimeUnit.SECONDS.sleep(5);
        }
    }
}
