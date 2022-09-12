package com.demo.lyr.nio_pipe;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * 这个管道的例子真的是非常差劲：https://www.bilibili.com/video/BV1E64y1h7Z4?p=27
 */
public class PipeTest {

    @Test
    public void test() throws Exception{
        // 获取管道
        Pipe pipe = Pipe.open();

        // 获取sink通道
        Pipe.SinkChannel sinkChannel = pipe.sink();

        // 创建缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("这是传输数据".toString().getBytes());

        // 写入数据
        byteBuffer.flip();
        sinkChannel.write(byteBuffer);

        // 获取source通道
        Pipe.SourceChannel sourceChannel = pipe.source();

        // 创建缓冲区，读取数据
        byteBuffer.flip();
        int len = sourceChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array(),0,len));

        // 关闭通道
        sourceChannel.close();
        sinkChannel.close();

    }

}
