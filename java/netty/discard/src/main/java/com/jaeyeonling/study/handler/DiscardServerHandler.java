package com.jaeyeonling.study.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {
    @Override
    protected void channelRead0(
            final ChannelHandlerContext ctx,
            final Object msg
    ) {
        // Empty
    }

    @Override
    public void exceptionCaught(
            final ChannelHandlerContext ctx,
            final Throwable cause
    ) {
        if (log.isErrorEnabled()) {
            log.error("Discard Server Handler Error", cause);
        }

        ctx.close();
    }
}
