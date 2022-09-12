package com.demo.lyr.nio_channel;


import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel写操作
 */
public class FileChannelWrite {
    public static void main(String[] args)throws Exception{

        // 创建FileChannel
        RandomAccessFile aFile = new RandomAccessFile("src/main/java/com/demo/nio/b.txt", "rw");
        FileChannel channel = aFile.getChannel();

        // 创建buffer
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 需要写入的数据
        String newData = "写入内容";

        buffer.clear();

        // 将数据写入缓冲区
        buffer.put(newData.getBytes());

        // 将写模式切换为读模式
        buffer.flip();

        // 读取缓冲区的数据，写入channel中
        // 因为无法保证write()方法一次能向channel中写入多少字节，因此需要反复调用write方法，直到buffer中没有尚未写入的数据
        while (buffer.hasRemaining()){
            // 从buffer中读取数据
            channel.write(buffer);
        }

        channel.close();

    }
}
