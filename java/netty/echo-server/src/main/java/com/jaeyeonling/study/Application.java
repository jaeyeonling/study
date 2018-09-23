package com.jaeyeonling.study;

import com.jaeyeonling.study.server.EchoServer;

public class Application {
    public static void main(final String... args) {
        final var server = new EchoServer();

        server.run();
    }
}
