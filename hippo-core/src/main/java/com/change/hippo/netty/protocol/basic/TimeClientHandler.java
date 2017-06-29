package com.change.hippo.netty.protocol.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * User: change.long@99bill.com
 * Date: 2017/6/26
 * Time: 下午3:39
 */
public class TimeClientHandler extends ChannelHandlerAdapter {


    private int counter;
    private byte[] req;

    public TimeClientHandler() {
        req = ("change.long" + System.getProperty("line.separator")).getBytes();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf firstByteBuf;
        for (int i = 0; i < 100; i++) {

            firstByteBuf = Unpooled.buffer(req.length);
            firstByteBuf.writeBytes(req);
            ctx.writeAndFlush(firstByteBuf);
        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        byte[] resp = new byte[buf.readableBytes()];
//        buf.readBytes(resp);
//        String body = new String(resp, "UTF-8");
        String body = (String) msg;
        System.out.println("The time client receive  : " + body + ",counter=" + ++counter);

    }

}
