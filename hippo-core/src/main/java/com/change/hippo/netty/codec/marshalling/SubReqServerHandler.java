package com.change.hippo.netty.codec.marshalling;

import com.change.hippo.netty.codec.protobuf.SubscribeReqProto;
import com.change.hippo.netty.codec.protobuf.SubscribeRespProto;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * User: change.long@99bill.com
 * Date: 2017/7/3
 * Time: 下午4:58
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    private int counter;

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq) msg;
        System.out.println("The sub server server receive order : " + req + ",counter=" + ++counter);
        if (req != null && req.getUserName().equalsIgnoreCase("change.long")) {
            ctx.writeAndFlush(resp(req));
        }

    }

    private SubscribeRespProto.SubscribeResp resp(SubscribeReqProto.SubscribeReq req) {
        if (null != req) {
            SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();
            return builder.setSubReqId(req.getSubReqId()).setRespCode(0)
                    .setDesc("Netty book order succeed, 3 days later, sent to the designated address").build();
        }
        return null;
    }
}
