package com.demo.lyr.nio_channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 都不知道这些代码能够做什么
 */
public class SocketChannelDemo {
    public static void main(String[] args) throws  Exception{

        // 创建SocketChannel
        InetSocketAddress address = new InetSocketAddress("www.baidu.com", 80);
        SocketChannel socketChannel = SocketChannel.open(address);

        boolean isOpen = socketChannel.isOpen();                           // 是否为open状态
        boolean isConnected = socketChannel.isConnected();                 // 是否已经被连接
        boolean isConnectionPending = socketChannel.isConnectionPending(); // 是否正在进行连接
        boolean isFinishConnect = socketChannel.finishConnect();           // 是否完成连接

        // 阻塞模式：true 非阻塞模式：false
        socketChannel.configureBlocking(false);

        // 读操作
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        socketChannel.read(byteBuffer);
        socketChannel.close();
        System.out.println("read over");

    }
}
