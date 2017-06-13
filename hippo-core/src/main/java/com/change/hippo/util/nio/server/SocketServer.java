package com.change.hippo.util.nio.server;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/26
 * Time: 下午3:05
 */
public class SocketServer {

    public static void main(String[] args) throws Exception {
        testServerSocketChannel();
    }

    private static void testServerSocketChannel() throws Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress socketAddress = new InetSocketAddress("127.0.0.1", 18080);
        serverSocketChannel.socket().bind(socketAddress);
        serverSocketChannel.configureBlocking(false);

        while (true) {
            SocketChannel socketChannel = serverSocketChannel.accept();
            if (socketChannel != null) {
                ByteBuffer byteBuffer = ByteBuffer.allocate(48);
                byteBuffer.clear();
                int byteReads = socketChannel.read(byteBuffer);

                while (byteReads != -1) {
                    byteBuffer.flip();  //make buffer ready for read
                    while (byteBuffer.hasRemaining()) {
                        System.out.println(byteBuffer.get());
                    }

                    byteBuffer.clear(); //make buffer ready for writing
                    byteReads = socketChannel.read(byteBuffer);

                }
                socketChannel.close();
            }
        }
    }
}
