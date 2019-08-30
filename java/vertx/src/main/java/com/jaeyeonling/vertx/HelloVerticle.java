package com.jaeyeonling.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloVerticle extends AbstractVerticle {

    @Override
    public void start(final Promise<Void> startPromise) {
        log.info("Start!!");
        startPromise.complete();
    }

    @Override
    public void stop(final Promise<Void> stopPromise) {
        log.info("Stop!!");
        stopPromise.complete();
    }
}
