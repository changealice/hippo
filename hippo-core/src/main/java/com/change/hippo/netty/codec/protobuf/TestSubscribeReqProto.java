package com.change.hippo.netty.codec.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/30
 * Time: 下午4:52
 */
public class TestSubscribeReqProto {


    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq subscribeReq = createSubscribeReq();
        System.out.println("Before decode :" + subscribeReq.toString());
        byte[] req = encode(subscribeReq);
        SubscribeReqProto.SubscribeReq req1 = decode(req);

        System.out.println("After decode :" + req1);
        System.out.println("equals=" + subscribeReq.equals(req1));
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] req) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(req);
    }


    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder()
                .setSubReqId(1)
                .setUserName("change.long")
                .setProductName("Netty Book");
        List<String> address = new ArrayList<String>();
        address.add("Beijing");
        address.add("shanghai");
        address.add("an hui");
        builder.addAllAddress(address);
        return builder.build();
    }
}
