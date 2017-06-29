package com.change.hippo.netty.aio;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/19
 * Time: 下午4:02
 * 基于aio的时间服务器
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 28082;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }

        AsyncTimeServerHandler asyncTimeServerHandler = new AsyncTimeServerHandler(port);
        new Thread(asyncTimeServerHandler,"AIO-AsyncTimeServerHandler-001").start();

    }
}
