package com.iflytek.staff.chao.server;

import com.iflytek.staff.chao.handler.Handler;
import com.iflytek.staff.chao.handler.HttpHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

@Slf4j
public class HttpServer {

    public static void main(String[] args) {
        try {
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(10000));
            ssc.configureBlocking(false);
            Selector selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            Handler handler = new Handler(1024);
            while (true) {
                if (selector.select(3000) == 0) {
                    System.out.println("等待超时");
                    continue;
                }
                System.out.println("处理请求。。");
                Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator();
                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();
                    new Thread(new HttpHandler(key)).run();
                    keyIterator.remove();
                }
            }
        } catch (Exception e) {
            log.error("ServerBIO error", e);
        }
    }

}

