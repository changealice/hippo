package com.change.hippo.netty.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/31
 * Time: 上午10:59
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 28080;

        if (null != args && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
                //默认值
            }
        }
        ServerSocket server = null;
        try {
            server = new ServerSocket(port);
            System.out.println("The time server is start in port : " + port);

            //采用线程池
            TimeServerHandlerExecutePool pool = new TimeServerHandlerExecutePool(10, 1000);
            while (true) {
                //同步堵塞
                Socket socket = server.accept();
                pool.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (server != null) {
                    System.out.println("the time server close");
                    server.close();
                    server = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
