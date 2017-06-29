package com.change.hippo.netty.protocol.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * User: change.long@99bill.com
 * Date: 2017/6/26
 * Time: 下午3:35
 */
public class NettyTimeClient {

    public static void main(String[] args) {
        int port = 28083;
        if (args != null && args.length > 0) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignore) {
            }
        }

        String host = "127.0.0.1";

        NioEventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(workGroup).channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        //解决TCP 粘包 拆包
                        pipeline.addLast(new LineBasedFrameDecoder(1024));
                        pipeline.addLast(new StringDecoder());
                        pipeline.addLast(new TimeClientHandler());
                    }
                });

        try {
            ChannelFuture f = bootstrap.connect(host, port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }


    }
}
