package com.jaeyeonling.vertx;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.util.internal.ConcurrentSet;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BulkJSONDelimitedTCPServerVerticle extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) {
        final var server = getVertx().createNetServer();
        server.connectHandler(socket -> {

            final List<Buffer> chunks = new ArrayList<>();

            socket.handler(buffer -> {
                log.info("Receive [socket={}, data={}]", socket, buffer.toString());
                chunks.add(buffer);
            });

            socket.closeHandler(event -> {
                log.info("Close [socket={}]", socket);
            });

            socket.endHandler(event -> {
                log.info("End [socket={}]", socket);
                chunks.stream()
                        .map(Buffer::toJson)
                        .forEach(json -> log.info("JSON: {}", json));
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
