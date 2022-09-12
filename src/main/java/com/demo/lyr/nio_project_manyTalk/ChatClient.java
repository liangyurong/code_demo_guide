package com.demo.lyr.nio_project_manyTalk;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws Exception{
      new ChatClient().startClient("小C");
    }

    public void startClient(String name) throws Exception{
        // 连接服务端
        SocketChannel channel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8000));
        channel.configureBlocking(false);

        // 接收服务端响应消息
        Selector selector = Selector.open();
        channel.register(selector, SelectionKey.OP_READ);

        // 创建线程 为什么这里要创建线程？创建线程的目的是什么？只是区分多个用户吗
        new Thread(new ClientThread(selector)).start();

        // 向服务端发送消息
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()){
            String msg = sc.nextLine();
            if(msg.length()>0){
                channel.write(Charset.forName("UTF-8").encode(name+" ："+msg));
            }
        }

    }

}
