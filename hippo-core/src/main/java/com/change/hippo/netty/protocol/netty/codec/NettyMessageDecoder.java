package com.change.hippo.netty.protocol.netty.codec;

import com.change.hippo.netty.protocol.netty.struct.Header;
import com.change.hippo.netty.protocol.netty.struct.NettyMessage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * User: change.long@99bill.com
 * Date: 2017/7/6
 * Time: 下午4:44
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {

    private MarshallingDecoder marshallingDecoder;

    public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset,
                               int lengthFieldLength) throws IOException {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
        this.marshallingDecoder = new MarshallingDecoder();
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        NettyMessage message = new NettyMessage();
        Header header = new Header();
        header.setCrcCode(frame.readInt());
        header.setLength(frame.readInt());
        header.setSessionID(frame.readLong());
        header.setType(frame.readByte());
        header.setPriority(frame.readByte());

        int size = frame.readInt();
        if (size > 0) {
            Map<String, Object> attach = new HashMap<String, Object>(size);

            for (int i = 0; i < size; i++) {
                int keySize = frame.readInt();
                byte[] keyArray = new byte[keySize];
                frame.readBytes(keyArray);
                String key = new String(keyArray, "UTF-8");
                attach.put(key, marshallingDecoder.decode(frame));
            }
            header.setAttachment(attach);
        }
        if (frame.readableBytes() > 4) {
            message.setBody(marshallingDecoder.decode(frame));
        }
        message.setHeader(header);
        return message;
    }
}
