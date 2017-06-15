package com.change.hippo.netty.nio;

import com.change.hippo.util.SafeDateFormat;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/15
 * Time: 下午3:47
 */
public class MultiplexerTimeServer implements Runnable {

    private ServerSocketChannel acceptorSvr;
    private Selector selector;

    private volatile boolean stop;

    public MultiplexerTimeServer(int port) {

        try {
            //1.ServerSocketChannel
            acceptorSvr = ServerSocketChannel.open();
            acceptorSvr.configureBlocking(false);

            //2.绑定监听地址 InetSocketAddress
            acceptorSvr.socket().bind(new InetSocketAddress(InetAddress.getByName("localhost"), port), 1024);

            //3.创建 Selector
            selector = Selector.open();

            //4.ServerSocketChannel 注册到 Selector,监听
            acceptorSvr.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port : " + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectionKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey selectionKey = keyIterator.next();
                    keyIterator.remove();
                    try {
                        handleSelectorKey(selectionKey);
                    } catch (IOException e) {
                        e.printStackTrace();
                        if (selectionKey != null) {
                            selectionKey.cancel();
                            if (selectionKey.channel() != null)
                                selectionKey.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // 多路复用器关闭后，所有注册在上面的Channel和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源

        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void handleSelectorKey(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                System.out.println("The key " + key + " is isAcceptable");
                ServerSocketChannel scc = (ServerSocketChannel) key.channel();
                SocketChannel sc = scc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }

            if (key.isReadable()) {
                System.out.println("The key " + key + " is isReadable");
                SocketChannel sc = (SocketChannel) key.channel();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();

                    byte[] bytes = new byte[byteBuffer.remaining()];
                    byteBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order : "
                            + body);
                    boolean flag = "change.long\r\n".equalsIgnoreCase(body);
                    String currentTime = flag ? SafeDateFormat.get().format(new Date()) : "BAD ORDER";
                    doWrite(sc, currentTime);
                } else {
                    // 对端链路关闭
                    key.cancel();
                    sc.close();
                }
            }
        }
    }

    private void doWrite(SocketChannel sc, String response) throws IOException {
        if (null != response && !"".equals(response)) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }
}
