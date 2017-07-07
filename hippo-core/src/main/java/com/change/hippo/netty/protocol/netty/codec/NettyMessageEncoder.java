package com.change.hippo.netty.protocol.netty.codec;

import com.change.hippo.netty.protocol.netty.struct.Header;
import com.change.hippo.netty.protocol.netty.struct.NettyMessage;

import java.io.IOException;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * User: change.long@99bill.com
 * Date: 2017/7/6
 * Time: 下午4:50
 */
public class NettyMessageEncoder extends MessageToByteEncoder<NettyMessage> {

    private MarshallingEncoder marshallingEncoder;

    public NettyMessageEncoder() throws IOException {
        this.marshallingEncoder = new MarshallingEncoder();
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, NettyMessage msg, ByteBuf sendBuf) throws Exception {
        if (null == msg || msg.getHeader() == null) {
            throw new Exception("message must be not null");
        }

        Header header = msg.getHeader();
        sendBuf.writeInt(header.getCrcCode());
        sendBuf.writeInt(header.getLength());
        sendBuf.writeLong(header.getSessionID());
        sendBuf.writeByte(header.getType());
        sendBuf.writeByte(header.getPriority());
        sendBuf.writeInt(header.getAttachment().size());

        //  附件
        for (Map.Entry<String, Object> param : header.getAttachment().entrySet()) {
            String key = param.getKey();
            Object value = param.getValue();
            byte[] keyArray = key.getBytes("UTF-8");
            sendBuf.writeInt(keyArray.length);
            sendBuf.writeBytes(keyArray);
            marshallingEncoder.encode(value, sendBuf);
        }

        //请求体
        if (msg.getBody() != null) {
            marshallingEncoder.encode(msg.getBody(), sendBuf);
        } else {
            sendBuf.writeInt(0);
        }

        sendBuf.setInt(4, sendBuf.readableBytes() - 8);
    }
}
