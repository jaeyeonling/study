package com.jaeyeonling.study.handler;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class EchoClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelActive(final ChannelHandlerContext ctx) {
        final var message = "Hello, Netty!";

        final var messageBuffer = Unpooled.buffer();
        messageBuffer.writeBytes(message.getBytes());

        if (log.isDebugEnabled()) {
            log.debug("Echo Client Request [msg={}]", message);
        }

        ctx.writeAndFlush(messageBuffer);
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) {
        ctx.close();
    }

    @Override
    public void exceptionCaught(
            final ChannelHandlerContext ctx,
            final Throwable cause
    ) {
        if (log.isErrorEnabled()) {
            log.error("Echo Client Handler Error", cause);
        }

        ctx.close();
    }
}
