package com.change.hippo.netty.nio;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/31
 * Time: 下午5:56
 */
public class TimeClient {


    public static void main(String[] args) {
        int port = 28081;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }

        //启动多路复用时间服务器
        MultiplexerTimeClientHandler multiplexerTimeClientHandler = new MultiplexerTimeClientHandler("127.0.0.1",port);

        new Thread(multiplexerTimeClientHandler,"NIO-MultiplexerTimeClient-001").start();
    }
}
