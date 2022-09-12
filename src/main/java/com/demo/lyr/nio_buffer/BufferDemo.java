package com.demo.lyr.nio_buffer;

import org.junit.Test;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class BufferDemo {


    @Test
    public void bufferRead() throws Exception{
        RandomAccessFile aFile = new RandomAccessFile("src/main/java/com/demo/nio_channel/a.txt", "rw");
        FileChannel channel = aFile.getChannel();

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据，写入到buffer
        int bytesRead = channel.read(buffer);

        while (bytesRead != -1){
            buffer.flip();
            while (buffer.hasRemaining()){
                System.out.println((char) buffer.get());
            }
            buffer.clear();
            bytesRead = channel.read(buffer);
        }

        aFile.close();
    }

    @Test
    public void bufferRead1(){
        IntBuffer buffer = IntBuffer.allocate(8);

        // 将数据放入buffer
        for (int i = 0; i < buffer.capacity(); i++) {
            int j = i*2+1;
            buffer.put(j);
        }

        // 将写模式转为读模式
        buffer.flip();

        // 从buffer取出数据
        while (buffer.hasRemaining()){
            int value = buffer.get();
            System.out.println(value+" ");
        }
    }

}
