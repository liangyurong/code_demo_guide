package com.demo.lyr.nio_buffer;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

// 直接缓冲区
// 操作：通过直接缓冲区复制文件
public class DirectBuffer {

    @Test
    public void test() throws Exception{
        FileInputStream fis = new FileInputStream("src/main/java/com/demo/nio_channel/a.txt");
        FileChannel inputChannel = fis.getChannel();

        FileOutputStream fos = new FileOutputStream("src/main/java/com/demo/nio_channel/b.txt");
        FileChannel outputChannel = fos.getChannel();

        // 创建直接缓冲区
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        // 复制内容
        while (true){
            buffer.clear();
            int read = inputChannel.read(buffer);
            if(read == -1){
                return;
            }
            buffer.flip(); //
            outputChannel.write(buffer);
        }


    }

}
