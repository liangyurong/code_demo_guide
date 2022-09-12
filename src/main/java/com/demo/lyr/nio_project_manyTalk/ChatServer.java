package com.demo.lyr.nio_project_manyTalk;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

public class ChatServer {
    public static void main(String[] args) throws Exception {
        new ChatServer().startServer();
    }

    public void startServer() throws Exception{
        // 选择器
        Selector selector = Selector.open();
        // 通道
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        // 通道绑定地址
        serverChannel.bind(new InetSocketAddress(8000));
        // 设置通道为非阻塞状态
        serverChannel.configureBlocking(false);
        // 通道注册到选择器，进行监听
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            // 获取channel数量
            int readChannels = selector.select();
            if(readChannels==0){
                continue;
            }
            // 获取可用channel
            Set<SelectionKey> keys = selector.selectedKeys();
            // 遍历
            Iterator<SelectionKey> it = keys.iterator();
            while (it.hasNext()){
                SelectionKey key = it.next();
                // 判断key的类型
                if(key.isAcceptable()){
                    acceptOperation(serverChannel,selector);
                }else if(key.isReadable()){
                    readOperation(selector,key);
                }else if(key.isConnectable()){

                }else if(key.isWritable()){

                }
                // 移除集合当前的key
                it.remove();

            }

        }

    }

    // 处理接入状态
    private void acceptOperation(ServerSocketChannel serverChannel, Selector selector) throws Exception {
        // 获取连接
        SocketChannel accept = serverChannel.accept();
        // 切换非阻塞模式
        accept.configureBlocking(false);
        // 注册
        accept.register(selector,SelectionKey.OP_READ); // 为什么变成read？
        // 客户端回复信息
        accept.write(Charset.forName("UTF-8").encode("欢迎进入聊天室，请注意隐私安全"));
    }

    // 处理可读状态
    private void readOperation(Selector selector,SelectionKey key) throws Exception {
        SocketChannel channel =(SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 读取数据
        int len =channel.read(byteBuffer);
        String msg = "";
        if(len>0){
            byteBuffer.flip();
            msg+=Charset.forName("UTF-8").decode(byteBuffer);
            byteBuffer.clear();
        }

        channel.register(selector,SelectionKey.OP_READ);

        // 广播给其他客户端
        if(msg.length()>0){
            System.out.println(msg);
            castOtherClient(msg,selector,channel);
        }

    }

    // 广播给其他客户端
    private void castOtherClient(String msg, Selector selector, SocketChannel channel) throws Exception  {
        Set<SelectionKey> keys = selector.keys();

        for (SelectionKey key : keys) {
            SelectableChannel targetChannel = key.channel();
            // 不需要给自己发送
            if(targetChannel instanceof SocketChannel && targetChannel != channel){
                ((SocketChannel) targetChannel).write(Charset.forName("UTF-8").encode(msg));
            }
        }
    }

}
