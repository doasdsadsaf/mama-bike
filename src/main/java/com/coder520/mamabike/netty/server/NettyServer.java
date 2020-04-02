package com.coder520.mamabike.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author 闪电侠
 */
public class NettyServer {
    public static void main(String[] args) {
        //创建一个监听线程 监听8000端口
        NioEventLoopGroup bossGroup = new NioEventLoopGroup();
        //创建一个管理线程组 处理每一条连接的线程
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();
        // 创建一个引导类
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //，必须要指定三类属性，分别是线程模型、IO 模型、连接读写处理逻辑
        serverBootstrap
                // 配置监听线程和管理线程  线程模型
                .group(bossGroup, workerGroup)
                // 指定服务端IO模型为NIO  IO模型
                .channel(NioServerSocketChannel.class)
                // 创建一个 ChannelInitializer 通道初始化器   连接读写处理逻辑
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new FirstServerHandler());
                    }
                });
        // 监听端口号
      //  serverBootstrap.bind(8000);
        // 判断监听端口是否绑定成功,从1000开始
        bind(serverBootstrap,8888);
    }

    /**
     * 递增绑定端口号方法
     * @param serverBootstrap
     * @param port
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}