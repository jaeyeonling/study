package com.jaeyeonling.study;

import com.jaeyeonling.study.server.DiscardServer;

public class Application {
    public static void main(final String... args) {
        final var server = new DiscardServer();

        server.run();
    }
}
