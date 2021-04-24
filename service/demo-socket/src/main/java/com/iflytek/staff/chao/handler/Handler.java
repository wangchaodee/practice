package com.iflytek.staff.chao.handler;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class Handler {
    private int bufferSize = 1024;
    private String localCharset = "UTF-8";

    public Handler() {

    }

    public Handler(int bufferSize) {
        this(bufferSize, null);
    }

    public Handler(String localCharset) {
        this(-1, localCharset);
    }

    public Handler(int bufferSize, String localCharset) {
        if (bufferSize > 0) this.bufferSize = bufferSize;
        if (localCharset != null) this.localCharset = localCharset;
    }

    public void handleAccept(SelectionKey key) throws IOException {
        SocketChannel sc = ((ServerSocketChannel) key.channel()).accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocate(bufferSize));
    }

    public void handleRead(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = (ByteBuffer) key.attachment();
        buffer.clear();
        if (sc.read(buffer) == -1) {
            sc.close();
        } else {
            buffer.flip();
            String receivedString = Charset.forName(localCharset).newDecoder().decode(buffer).toString();
            System.out.println("received from client : " + receivedString);
            String sendString = "received data : " + receivedString;
            buffer = ByteBuffer.wrap(sendString.getBytes(localCharset));
            sc.write(buffer);
            sc.close();
        }

    }
}
