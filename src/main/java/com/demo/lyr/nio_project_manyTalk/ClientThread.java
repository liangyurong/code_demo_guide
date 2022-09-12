package com.demo.lyr.nio_project_manyTalk;

import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * 客户端线程类
 */
public class ClientThread implements Runnable {

    private Selector selector;

    public ClientThread(Selector selector) {
        this.selector = selector;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // 获取channel数量
                int readChannels = 0;
                readChannels = selector.select();
                if (readChannels == 0) {
                    continue;
                }
                // 获取可用channel
                Set<SelectionKey> keys = selector.selectedKeys();
                // 遍历
                Iterator<SelectionKey> it = keys.iterator();
                while (it.hasNext()) {
                    SelectionKey key = it.next();
                    // 判断key的类型
                    if (key.isReadable()) {
                        readOperation(selector, key);
                    }
                    // 移除集合当前的key
                    it.remove();
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    // 处理可读状态
    private void readOperation(Selector selector, SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        // 读取数据
        int len = channel.read(byteBuffer);
        String msg = "";
        if (len > 0) {
            byteBuffer.flip();
            msg += Charset.forName("UTF-8").decode(byteBuffer);
            byteBuffer.clear();
        }

        channel.register(selector, SelectionKey.OP_READ);

        if (msg.length() > 0) {
            System.out.println(msg);
        }

    }

}
