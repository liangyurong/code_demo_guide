package com.demo.lyr.nio_channel;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

/**
 * 实现通道之间的数据传输: 将fromChannel的数据传输到toChannel
 */
public class FileChannelTransfer {
    public static void main(String[] args) throws Exception{

        // 创建FileChannel
        RandomAccessFile aFile = new RandomAccessFile("src/main/java/com/demo/nio/a.txt", "rw");
        FileChannel fromChannel = aFile.getChannel();

        // 创建FileChannel
        RandomAccessFile bFile = new RandomAccessFile("src/main/java/com/demo/nio/b.txt", "rw");
        FileChannel toChannel = bFile.getChannel();

        // 将fromChannel的数据传输到toChannel
        long position = toChannel.size(); // 起始位置
        long size = fromChannel.size();
        toChannel.transferFrom(fromChannel,position,size);

        aFile.close();
        bFile.close();

    }
}
