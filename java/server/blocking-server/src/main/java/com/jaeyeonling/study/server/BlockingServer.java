package com.jaeyeonling.study.server;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.ServerSocket;

@Slf4j
public class BlockingServer extends ServerSocket {
    public BlockingServer(int port) throws IOException {
        super(port);
    }

    //
    //

    public void run() throws IOException {
        if (log.isDebugEnabled()) {
            log.debug("Waiting for connection...");
        }


        while (true) {
            final var sock = accept();
            if (log.isDebugEnabled()) {
                log.debug("Client connected");
            }

            final var out = sock.getOutputStream();
            final var in = sock.getInputStream();

            while (true) {
                try {
                    out.write(in.read());
                } catch (final IOException e) {
                    break;
                }
            }
        }
    }
}
