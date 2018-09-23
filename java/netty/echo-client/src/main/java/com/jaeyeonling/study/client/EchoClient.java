package com.jaeyeonling.study.client;

import com.jaeyeonling.study.handler.EchoClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoClient {
    private final EventLoopGroup group = new NioEventLoopGroup();
    private final Bootstrap b = new Bootstrap();

    //
    //

    public void run() {
        try {
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(final SocketChannel ch) {
                            final var p = ch.pipeline();
                            p.addLast(new EchoClientHandler());
                        }
                    });

            final var f = b.connect("localhost", 8888).sync();

            f.channel().closeFuture().sync();
        } catch(final InterruptedException e) {
            if (log.isErrorEnabled()) {
                log.error("Echo Client Error", e);
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
