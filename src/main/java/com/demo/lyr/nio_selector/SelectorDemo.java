package com.demo.lyr.nio_selector;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class SelectorDemo {

    // 客户端代码
    @Test
    public void client() throws Exception{
        // 创建channel
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 8888);
        SocketChannel channel = SocketChannel.open(address);

        // 切换为非阻塞状态
        channel.configureBlocking(false);

        // 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 向buffer写入数据
        buffer.put(("当前时间："+new Date()).getBytes());

        // 模式切换
        buffer.flip();

        // 写入通道
        channel.write(buffer);

        // 关闭buffer
        buffer.clear();

        // 关闭SocketChannel
        channel.close();
    }

    // 服务端代码
    @Test
    public void server()  throws Exception{
        // 获取服务端通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();

        // 切换非阻塞状态
        serverChannel.configureBlocking(false);

        // 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 绑定端口号
        InetSocketAddress address = new InetSocketAddress(8888); // 绑定客户端的端口号
        serverChannel.bind(address);

        // 获取selector选择器
        Selector selector = Selector.open();

        // 通道注册到选择器，进行监听
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 选择器进行轮询，进行后续操作
        while (selector.select()>0){
            Set<SelectionKey> keys = selector.selectedKeys();
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                // 判断key的类型
                if(key.isAcceptable()){
                    // 获取连接
                    SocketChannel accept = serverChannel.accept();
                    // 切换非阻塞模式
                    accept.configureBlocking(false);
                    // 注册
                    accept.register(selector,SelectionKey.OP_READ); // 为什么变成read？
                }else if(key.isReadable()){
                    SocketChannel channel =(SocketChannel) key.channel();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                    // 读取数据
                    int len =0;
                    while ((len = channel.read(byteBuffer))>0){
                        byteBuffer.flip();
                        System.out.println(new String(byteBuffer.array(),0,len));
                        byteBuffer.clear();
                    }
                }else if(key.isConnectable()){

                }else if(key.isWritable()){

                }
                it.remove();
            }
        }

        //

    }

}
