package com.jaeyeonling.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.net.NetSocket;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.function.Predicate;

@Slf4j
public class TCPChatServerVerticle extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) throws Exception {
        final var server = getVertx().createNetServer();
        final var sockets = new ArrayList<NetSocket>();

        server.connectHandler(socket -> {
            sockets.add(socket);

            socket.handler(buffer -> sockets.stream()
                    .filter(Predicate.not(Predicate.isEqual(socket)))
                    .forEach(other -> other.write(buffer)));

            socket.closeHandler(event -> {
                log.info("Close [socket={}]", socket);
                sockets.remove(socket);
            });

            socket.exceptionHandler(e -> {
                log.error("Exception [socket={}, exception={}]", socket, e);
            });
        });

        server.listen(8888, "localhost", event -> {
            log.info("bind result: {}", event.succeeded());
            startPromise.complete();
        });
        log.info("Start!!");
    }

    @Override
    public void stop(final Promise<Void> stopPromise) throws Exception {
        log.info("Stop!!");
        stopPromise.complete();
    }
}
