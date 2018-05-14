package com.change.hippo.netty.protocol.netty.server;

import com.change.hippo.netty.protocol.netty.MessageType;
import com.change.hippo.netty.protocol.netty.struct.Header;
import com.change.hippo.netty.protocol.netty.struct.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * User: change.long@99bill.com
 * Date: 2017/7/6
 * Time: 下午5:42
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        NettyMessage message = (NettyMessage) msg;
        // 返回心跳应答消息
        if (null != message && message.getHeader().getType() == MessageType.HEARTBEAT_REQ.value()) {
            System.out.println(Thread.currentThread().getName() + "Receive client heart beat message :" + message);
            NettyMessage heartBeat = buildHeartBeat();
            System.out.println(Thread.currentThread().getName() + "Send heart beat response message to client :" + heartBeat);
            ctx.writeAndFlush(heartBeat);
        } else {
            ctx.fireChannelRead(msg);
        }
    }

    private NettyMessage buildHeartBeat() {
        NettyMessage heartBeat = new NettyMessage();
        Header header = new Header();
        header.setType(MessageType.HEARTBEAT_RESP.value());
        heartBeat.setHeader(header);
        return heartBeat;
    }
}
