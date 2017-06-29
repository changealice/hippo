package com.change.hippo.netty.sign;

import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.io.IOException;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/19
 * Time: 下午2:47
 */
public class SignTestIT {


    public static void main(String[] args) throws IOException {


        String osName = System.getProperties().getProperty("os.name");

        //执行 KILL -SIGUSR1 -pid
        Signal signal = new Signal(osName.toLowerCase().startsWith("win") ? "INT" : "USR2");

        SignalHandler shutDownHandler = new SignalHandler() {
            @Override
            public void handle(Signal signal) {
                System.out.println("收到信号" + signal);
            }
        };
        Signal.handle(signal, shutDownHandler);


        System.in.read();
    }
}
