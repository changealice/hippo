package com.change.hippo.netty.protocol.netty.client;

import com.change.hippo.netty.protocol.netty.NettyConstant;
import com.change.hippo.netty.protocol.netty.codec.NettyMessageDecoder;
import com.change.hippo.netty.protocol.netty.codec.NettyMessageEncoder;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * User: change.long@99bill.com
 * Date: 2017/7/6
 * Time: 下午5:55
 */
public class NettyClient {


    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    //共享客户端连接池，不然会出现内存线程池泄露
    private NioEventLoopGroup workGroup = new NioEventLoopGroup();

    public static void main(String[] args) {
        new NettyClient().connect(NettyConstant.REMOTE_IP, NettyConstant.PORT);
    }

    private void connect(String ip, int port) {


        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                        pipeline.addLast("MessageEncoder", new NettyMessageEncoder());
                        pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(NettyConstant.READ_TIMEOUT));
                        pipeline.addLast("LoginAuthHandler", new LoginAuthReqHandler());
                        pipeline.addLast("HeartBeatHandler", new HeartBeatReqHandler());
                    }
                });

        try {
            ChannelFuture f = bootstrap.connect(ip, port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        connect(NettyConstant.REMOTE_IP, NettyConstant.PORT);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
