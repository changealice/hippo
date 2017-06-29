package com.change.hippo.netty.aio;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/21
 * Time: 上午10:28
 */
public class TimeClient {

    public static void main(String[] args) {

        int port = 28082;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }

        String host = "127.0.0.1";
        AsyncTimeClientHandler asyncTimeClientHandler = new AsyncTimeClientHandler(host, port);
        new Thread(asyncTimeClientHandler, "AIO-AsyncTimeClientHandler-001").start();
    }
}
