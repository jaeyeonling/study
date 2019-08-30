package com.jaeyeonling.vertx;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
public class ManualBulkJSONDelimitedTCPServerVerticle extends AbstractVerticle {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void start(final Promise<Void> startPromise) {
        final var server = getVertx().createNetServer();
        server.connectHandler(socket -> {

            final StringBuffer chunks = new StringBuffer();

            socket.handler(buffer -> {
                log.info("Receive [socket={}, data={}]", socket, buffer.toString());
                chunks.append(buffer.toString());
            });

            socket.closeHandler(event -> {
                log.info("Close [socket={}]", socket);
            });

            socket.endHandler(event -> {
                log.info("End [socket={}]", socket);
                Arrays.stream(chunks.toString().split(System.lineSeparator()))
                        .map(rawChunk -> {
                            try {
                                return objectMapper.readTree(rawChunk);
                            } catch (final IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
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
