package com.demo.lyr.nio_DataGramChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.charset.Charset;

public class DataGramChannelReceive {
    public static void main(String[] args) throws  Exception {
        InetSocketAddress receiveAddress = new InetSocketAddress("127.0.0.1", 9999);
        DatagramChannel receiveChannel = DatagramChannel.open();

        // 绑定
        receiveChannel.bind(receiveAddress);

        // 缓冲区
        ByteBuffer receiveBuffer = ByteBuffer.allocate(1024);

        // 一直循环接收
        while (true){
            receiveBuffer.clear();
            SocketAddress socketAddress = receiveChannel.receive(receiveBuffer);

            // 转换模式
            receiveBuffer.flip();

            System.out.println(socketAddress.toString());

            System.out.println(Charset.forName("UTF-8").decode(receiveBuffer));
        }

    }
}
