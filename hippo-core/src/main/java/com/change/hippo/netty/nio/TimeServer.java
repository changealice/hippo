package com.change.hippo.netty.nio;

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

        //启动多路复用时间服务器客户端
        MultiplexerTimeServer multiplexerTimeServer = new MultiplexerTimeServer(port);

        new Thread(multiplexerTimeServer,"NIO-MultiplexerTimeServer-001").start();
    }
}
