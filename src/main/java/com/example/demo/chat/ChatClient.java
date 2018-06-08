package com.example.demo.chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Author TAO
 * @Date 2018/6/8 17:05
 */
public class ChatClient {

    private int port;
    private String host;

    public ChatClient(String localhost, int port) {
        this.host = localhost;
        this.port = port;
    }

    public void run() throws InterruptedException, IOException {
        NioEventLoopGroup boss = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(boss)
                    .channel(NioSocketChannel.class)
                    .handler(new SimpleChatClientInitializer());

            Channel channel = bootstrap.connect(host, port).sync().channel();

            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("循环输入");
            while (true){
                channel.writeAndFlush(in.readLine()+"\r\n");
            }

        }finally {
            boss.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        int port = 9000;
        new ChatClient("localhost", port).run();
    }
}
