package com.demo.lyr.nio_DataGramChannel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class DataGramChannelSend {
    public static void main(String[] args) throws  Exception {

        InetSocketAddress sendAddress = new InetSocketAddress("127.0.0.1", 9999);
        DatagramChannel sendChannel = DatagramChannel.open();

        // 一直循环发送
        while (true){
            ByteBuffer buffer = ByteBuffer.wrap("需要发送的数据".getBytes("UTF-8"));
            sendChannel.send(buffer,sendAddress);
            System.out.println("发送端已经完成发送");
            Thread.sleep(1000);
        }

    }
}
