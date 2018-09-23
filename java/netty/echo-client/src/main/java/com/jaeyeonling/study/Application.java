package com.jaeyeonling.study;

import com.jaeyeonling.study.client.EchoClient;

public class Application {
    public static void main(final String... args) {
        final var client = new EchoClient();

        client.run();
    }
}
