package com.jaeyeonling.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JSONDelimitedTCPServerVerticle extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) {
        final var server = getVertx().createNetServer();
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                log.info("Receive [socket={}, data={}]", socket, buffer.toString());
                log.info("JSON: {}", buffer.toJson());
            });

            socket.closeHandler(event -> {
                log.info("Close [socket={}]", socket);
            });

            socket.endHandler(event -> {
                log.info("End [socket={}]", socket);
            });

            socket.drainHandler(event -> {
               log.info("Drain [socket={}]", socket);
            });

            socket.exceptionHandler(e -> {
                log.error("Exception [socket={}, exception={}]", socket, e);
            });
        });
        server.listen(8888, "localhost", event -> {
            log.info("bind result: {}", event.succeeded());
            startPromise.complete();
        });
    }

    @Override
    public void stop(final Promise<Void> stopPromise) {
        log.info("Stop!!");
        stopPromise.complete();
    }
}
