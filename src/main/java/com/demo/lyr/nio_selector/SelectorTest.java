package com.demo.lyr.nio_selector;

import org.junit.Test;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SelectorTest {

    @Test
    public void createSelector() throws Exception {
        // 创建selector
        Selector selector = Selector.open();

        // 创建channel
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // channel必须处于非阻塞模式
        serverSocketChannel.configureBlocking(false);

        // 绑定连接
        serverSocketChannel.bind(new InetSocketAddress(9999));

        // 将channel注册到selector
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        // 查询已经就绪的通道
        Set<SelectionKey> selectionKeys = selector.selectedKeys();

        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()){
            SelectionKey key = iterator.next();
            if(key.isAcceptable()){

            }
            if(key.isConnectable()){

            }
            if(key.isReadable()){

            }
            if(key.isWritable()){

            }
            // 为什么要remove？
            iterator.remove();
        }

    }


}
