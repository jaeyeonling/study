package com.jaeyeonling.study.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.Charset;


@Slf4j
public class EchoServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(
            final ChannelHandlerContext ctx,
            final Object msg
    ) {
        if (log.isDebugEnabled()) {
            log.debug(
                    "Echo Server Read [msg={}]",
                    ((ByteBuf) msg).toString(Charset.defaultCharset())
            );
        }

        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(final ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(
            final ChannelHandlerContext ctx,
            final Throwable cause
    ) {
        if (log.isErrorEnabled()) {
            log.error("Echo Server Handler Error", cause);
        }

        ctx.close();
    }
}
