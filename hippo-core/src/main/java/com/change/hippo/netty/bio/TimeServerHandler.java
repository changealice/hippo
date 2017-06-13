package com.change.hippo.netty.bio;

import com.change.hippo.util.SafeDateFormat;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

/**
 * User: change.long@99bill.com
 * Date: 2017/5/31
 * Time: 上午11:03
 */
public class TimeServerHandler implements Runnable {
    private final Socket socket;

    public TimeServerHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        println("TimeServerHandler begin a new");
        Scanner scanner = null;
        PrintWriter pw = null;
        try {
            //读取客户端输入
            scanner = new Scanner(this.socket.getInputStream());
            String line;
            //向客户端写入
            pw = new PrintWriter(this.socket.getOutputStream(), true);
            while (true) {
                line = scanner.nextLine();
                if (line == null) {
                    break;
                }
                println("收到报文：" + line);
                if (line.equalsIgnoreCase("change.long")) {
                    String currentTime = SafeDateFormat.get().format(new Date());
                    pw.println(currentTime);
                } else {
                    pw.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
            if (pw != null) {
                pw.close();
            }
            try {
                SafeDateFormat.remove();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void println(String x) {
        System.out.println(x + ",thread= " + Thread.currentThread().getName());
    }
}
