package com.change.hippo.netty.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/31
 * Time: 下午5:56
 */
public class TimeServer {


    public static void main(String[] args) {
        int port = 28081;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }
        ServerSocketChannel acceptSvc = null;
        try {
            acceptSvc = ServerSocketChannel.open();
            InetAddress ia = InetAddress.getByName("localhost");
            acceptSvc.socket().bind(new InetSocketAddress(ia, port));
            acceptSvc.configureBlocking(false);

            Selector selector = Selector.open();
            SelectionKey selectionKey = acceptSvc.register(selector, SelectionKey.OP_ACCEPT);

            int num = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while (iterator.hasNext()){
                SelectionKey sKey = iterator.next();
                SelectableChannel channel = sKey.channel();
                if (channel instanceof SocketChannel){
                    SocketChannel socketChannel = (SocketChannel) channel;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (acceptSvc != null) {
                    acceptSvc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
