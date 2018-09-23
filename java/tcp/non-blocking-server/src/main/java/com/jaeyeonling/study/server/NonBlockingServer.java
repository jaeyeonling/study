package com.jaeyeonling.study.server;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@AllArgsConstructor
public class NonBlockingServer {
    private final Map<SocketChannel, List<byte[]>> keepDataTrack = new HashMap<>();
    private final ByteBuffer buffer = ByteBuffer.allocate(2 * 1024);

    //
    //

    private final int port;

    //
    //

    public void run() throws IOException {
        try (
                final var selector = Selector.open();
                final var serverSocketChannel = ServerSocketChannel.open()
        ) {
            if (serverSocketChannel.isOpen() && selector.isOpen()) {
                serverSocketChannel.configureBlocking(false);
                serverSocketChannel.bind(new InetSocketAddress(port));
                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

                if (log.isDebugEnabled()) {
                    log.debug("Wait for connection...");
                }

                while (true) {
                    selector.select();

                    final var keys = selector.selectedKeys().iterator();

                    while (keys.hasNext()) {
                        final var key = keys.next();
                        keys.remove();

                        if (key.isValid()) {
                            if (key.isAcceptable()) {
                                acceptOP(key, selector);
                            } else if (key.isReadable()) {
                                readOP(key);
                            } else if (key.isWritable()) {
                                writeOP(key);
                            }
                        } else {
                            continue;
                        }
                    }
                }

            } else {
                if (log.isErrorEnabled()) {
                    log.error("Failed to create server socket");
                }
            }
        }
    }

    //
    //

    private void acceptOP(
            final SelectionKey key,
            final Selector selector
    ) throws IOException {
        final var serverChannel = (ServerSocketChannel) key.channel();
        final var socketChannel = serverChannel.accept();
        socketChannel.configureBlocking(false);

        if (log.isDebugEnabled()) {
            log.debug("Client connected [address={}]", socketChannel.getRemoteAddress());
        }

        keepDataTrack.put(socketChannel, new ArrayList<>());
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void readOP(final SelectionKey key) {
        try {
            final var socketChannel = (SocketChannel) key.channel();
            buffer.clear();

            var numRead = -1;
            try {
                numRead = socketChannel.read(buffer);
            } catch (final IOException e) {
                if (log.isErrorEnabled()) {
                    log.error("Data read error", e);
                }
            }

            if (numRead == -1) {
                keepDataTrack.remove(socketChannel);

                if (log.isDebugEnabled()) {
                    log.debug("Client connection close [address={}]", socketChannel.getRemoteAddress());
                }

                socketChannel.close();
                key.cancel();
            } else {
                final var data = new byte[numRead];
                System.arraycopy(buffer.array(), 0, data, 0, numRead);

                if (log.isDebugEnabled()) {
                    log.debug(
                            "{} from {}",
                            new String(data, StandardCharsets.UTF_8),
                            socketChannel.getRemoteAddress()
                    );
                }

                doEchoJob(key, data);
            }
        } catch (final IOException e) {
            if (log.isErrorEnabled()) {
                log.error("readOP Error", e);
            }
        }
    }

    private void writeOP(final SelectionKey key) throws IOException {
        final var socketChannel = (SocketChannel) key.channel();

        final var channelData = keepDataTrack.get(socketChannel);
        final var its = channelData.iterator();

        while (its.hasNext()) {
            final var it = its.next();
            its.remove();
            socketChannel.write(ByteBuffer.wrap(it));
        }

        key.interestOps(SelectionKey.OP_READ);
    }

    private void doEchoJob(
            final SelectionKey key,
            final byte[] data
    ) {
        final var socketChannel = (SocketChannel) key.channel();
        final var channelData = keepDataTrack.get(socketChannel);

        channelData.add(data);

        key.interestOps(SelectionKey.OP_WRITE);
    }
}
