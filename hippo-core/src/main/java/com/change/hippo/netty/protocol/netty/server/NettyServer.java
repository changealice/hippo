package com.change.hippo.netty.protocol.netty.server;

import com.change.hippo.netty.protocol.netty.NettyConstant;
import com.change.hippo.netty.protocol.netty.codec.NettyMessageDecoder;
import com.change.hippo.netty.protocol.netty.codec.NettyMessageEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * User: change.long@99bill.com
 * Date: 2017/7/6
 * Time: 下午4:36
 */
public class NettyServer {

    public static void main(String[] args) {
        new NettyServer().bind();
    }

    private void bind() {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup).option(ChannelOption.SO_BACKLOG, 512).
                    option(ChannelOption.TCP_NODELAY, true)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new NettyMessageDecoder(1024 * 1024, 4, 4));
                            pipeline.addLast(new NettyMessageEncoder());
                            pipeline.addLast("readTimeoutHandler", new ReadTimeoutHandler(NettyConstant.READ_TIMEOUT));
                            pipeline.addLast(new LoginAuthRespHandler());
                            pipeline.addLast(new HeartBeatRespHandler());
                        }
                    });

            ChannelFuture f = b.bind(NettyConstant.REMOTE_IP, NettyConstant.PORT);
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
