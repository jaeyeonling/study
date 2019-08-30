package com.jaeyeonling.vertx;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ManualJSONDelimitedTCPServerVerticle extends AbstractVerticle {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void start(final Promise<Void> startPromise) {
        final var server = getVertx().createNetServer();
        server.connectHandler(socket -> {
            socket.handler(buffer -> {
                log.info("Receive [socket={}, data={}]", socket, buffer.toString());
                try {
                    final var json = objectMapper.readTree(buffer.toString());
                    log.info("JSON: {}", json);
                } catch (final IOException e) {
                    throw new RuntimeException(e);
                }
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
