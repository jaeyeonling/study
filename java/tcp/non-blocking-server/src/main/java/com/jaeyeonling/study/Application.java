package com.jaeyeonling.study;

import com.jaeyeonling.study.server.NonBlockingServer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class Application {
    public static void main(final String... args) {
        try {
            final var server = new NonBlockingServer(8888);

            server.run();
        } catch (final IOException e) {
            if (log.isErrorEnabled()) {
                log.error("Non Blocking Server Error", e);
            }
        }
    }
}
