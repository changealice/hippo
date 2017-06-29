package com.change.hippo.netty.protocol.delimiter;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * User: change.long@99bill.com
 * Date: 2017/6/29
 * Time: 下午4:34
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private static volatile int counter = 0;
    private static final String ECHO_HEO = "Hi,change.long ,welcome to Netty$_";

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_HEO.getBytes()));
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String resp = (String) msg;
        System.out.println("The echo client server receive order : " + resp + ",counter=" + ++counter);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
