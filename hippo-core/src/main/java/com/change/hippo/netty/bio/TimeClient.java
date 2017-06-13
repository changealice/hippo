package com.change.hippo.netty.bio;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/31
 * Time: 上午11:32
 */
public class TimeClient {

    public static void main(String[] args) {

        int port = 28080;

        if (null != args && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }


        Socket socket = null;
        Scanner in = null;

        PrintWriter pw = null;
        try {
            socket = new Socket("127.0.0.1", port);
            println("connect The time server in port : " + port + " succeed");
            in = new Scanner(socket.getInputStream());
            //向服务器端写内容
            pw = new PrintWriter(socket.getOutputStream(), true);
            pw.println("change.long");//打印字符串并且终止该行


            String line = in.nextLine();
            println("收到服务器应答:" + line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (in != null) {
                in.close();
            }
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    private static void println(String x) {
        System.out.println(x + ",thread= " + Thread.currentThread().getName());
    }
}
