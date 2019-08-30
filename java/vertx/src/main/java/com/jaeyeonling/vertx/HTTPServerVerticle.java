package com.jaeyeonling.vertx;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HTTPServerVerticle extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) {
        final var server = getVertx().createHttpServer();
        server.requestHandler(request -> {
            log.info("Request [method={}, uri={}, path={}, query={}]",
                    request.method(), request.uri(), request.path(), request.query());

            request.params()
                    .entries()
                    .forEach(param -> log.info("Param [key={}, value={}]", param.getKey(), param.getValue()));

            request.headers()
                    .entries()
                    .forEach(header -> log.info("Header [key={}, value={}]", header.getKey(), header.getValue()));

            request.bodyHandler(buffer -> log.info("Receive [data={}]", buffer.toString()));

            request.response()
                    .setStatusCode(HttpResponseStatus.OK.code())
                    .end(HttpResponseStatus.OK.reasonPhrase());
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
