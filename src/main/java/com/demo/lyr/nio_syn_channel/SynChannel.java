package com.demo.lyr.nio_syn_channel;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class SynChannel {

    @Test
    public void writeByCompletionHandler() throws Exception {
        // 文件路径
        String filePath = "src/main/java/com/demo/nio_syn_channel/aa.txt";

        // 创建Path实例
        Path path = Paths.get(filePath);

        // 创建异步文件通道
        AsynchronousFileChannel fileChannel = AsynchronousFileChannel.open(path, StandardOpenOption.WRITE);

        // 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 将数据写入buffer
        buffer.put("教程真的很垃圾qqqqq".getBytes());
        buffer.flip();

        fileChannel.write(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
            // 读取成功会调用complete方法
            @Override
            public void completed(Integer result, ByteBuffer attachment) {
                System.out.println("result = " + result);
            }

            // 读取失败会调用failed方法
            @Override
            public void failed(Throwable exc, ByteBuffer attachment) {
                System.out.println("写入失败");
            }
        });

        fileChannel.close();
    }
}