package com.change.hippo.netty.protocol.fixlen;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/29
 * Time: 下午5:31
 */
public class FixLengthEchoClientHandler extends ChannelHandlerAdapter {

    private int counter = 0;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 20; i++) {
            ctx.writeAndFlush(Unpooled.copiedBuffer("12345678901234567890".getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String resp = (String) msg;
        System.out.println("The  echo client server receive order : " + resp + ",counter=" + ++counter);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
