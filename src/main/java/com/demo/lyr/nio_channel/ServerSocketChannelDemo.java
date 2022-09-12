package com.demo.lyr.nio_channel;



import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class ServerSocketChannelDemo {
    public static void main(String[] args) throws Exception{

        // 设置端口号
        int port =8111;

        // buffer
        ByteBuffer buffer = ByteBuffer.wrap("hello world".getBytes());

        ServerSocketChannel ssc = ServerSocketChannel.open();

        // 绑定
        InetSocketAddress socketAddress = new InetSocketAddress(port);
        ssc.socket().bind(socketAddress);

        // 设置非阻塞的模式
        ssc.configureBlocking(false);

        // 一直监听：有没有新的连接传入
        while (true){
            System.out.println("等待新链接.....");
            // 在新链接进入之前，都会一直处于阻塞状态
            SocketChannel sc = ssc.accept();
            if(sc==null){
                System.out.println("没有新的连接传入");
                Thread.sleep(2000);
            }else {
                System.out.println("新连接地址："+sc.socket().getRemoteSocketAddress());

                // 将内容写入buffer
                buffer.rewind();// 指针为0 ?????
                sc.write(buffer);
                sc.close();
            }
        }

    }
}
