package com.change.hippo.netty.codec.marshalling;

import com.change.hippo.netty.codec.protobuf.SubscribeReqProto;

import java.util.ArrayList;
import java.util.List;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


/**
 * User: change.long@99bill.com
 * Date: 2017/7/3
 * Time: 下午5:11
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i = 0; i < 10; i++) {
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReqProto.SubscribeReq subReq(int seq) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        List<String> address = new ArrayList<>();
        address.add("NanJing YuHuaTai");
        address.add("BeiJing LiuLiChang");
        address.add("ShenZhen HongShuLin");
        return builder.setSubReqId(seq)
                .setProductName("Netty Book For Protobuf")
                .setUserName("change.long")
                .addAllAddress(address)
                .build();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Receive server response : [" + msg + "]");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
