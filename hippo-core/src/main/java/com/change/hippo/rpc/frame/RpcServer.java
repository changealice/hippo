package com.change.hippo.rpc.frame;

import com.change.hippo.rpc.annotation.RPCService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.HashMap;
import java.util.Map;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * User: change.long
 * Date: 16/4/10
 * Time: 下午8:25
 */
public class RpcServer implements ApplicationContextAware, InitializingBean {

    private final Logger logger = LoggerFactory.getLogger(RpcServer.class);
    private final String serverAddress;
    private final ServiceRegistry serviceRegistry;
    private ApplicationContext applicationContext;
    private HashMap<String, Object> handlerMap = new HashMap<String, Object>();

    public RpcServer(String serverAddress, ServiceRegistry serviceRegistry) {
        this.serverAddress = serverAddress;
        this.serviceRegistry = serviceRegistry;

    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        Map<String, Object> serviceBeanMap = applicationContext.getBeansWithAnnotation(RPCService.class);
        for (Object serviceBean : serviceBeanMap.values()) {
            String interfaceName = serviceBean.getClass().getAnnotation(RPCService.class).value().getName();
            handlerMap.put(interfaceName, serviceBean);
        }
    }

    public void afterPropertiesSet() throws Exception {
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        NioEventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(bossGroup, workGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline()
                                .addLast(new RpcDecoder(RpcRequest.class)) // 将 RPC 请求进行解码（为了处理请求）
                                .addLast(new RpcEncoder(RpcResponse.class)) // 将 RPC 响应进行编码（为了返回响应）
                                .addLast(new RpcHandler(handlerMap)); // 处理 RPC 请求

                    }
                }).option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);

        String array[] = serverAddress.split(":");
        String host = array[0];
        int port = Integer.parseInt(array[1]);
        ChannelFuture channelFuture = bootstrap.bind(host, port).sync();
        logger.info("server started on {}:{}", host, port);
        if (null != serviceRegistry) {
            serviceRegistry.register(serverAddress); // 注册服务地址
        }
        channelFuture.channel().closeFuture().sync();
    }
}
