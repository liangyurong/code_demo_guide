package com.demo.lyr.nio_channel;

import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * FileChannel读操作
 */
public class FileChannelRead {

    public static void main(String[] args) throws Exception{
        // 创建FileChannel
        RandomAccessFile aFile = new RandomAccessFile("src/main/java/com/demo/nio/a.txt", "rw");
        FileChannel channel = aFile.getChannel();

        // 创建buffer，分配2024字节
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        // 读取数据,写到buffer中 (返回的int值代表有多少字节读到了buffer中)
        int bytesRead = channel.read(buffer);

        // bytesRead=-1，说明读取到了文件末尾
        while (bytesRead != -1){
            System.out.println("读取了："+bytesRead);
            // 模式转换。由读模式变为写模式
            buffer.flip();
            // 当buffer里面有剩余的内容
            while (buffer.hasRemaining()){
                System.out.println((char) buffer.get());
            }
            // 将缓冲区清空
            buffer.clear();
            // 缓冲区清空，使得 bytesRead = -1
            bytesRead = channel.read(buffer);
        }

        aFile.close();
        System.out.println("操作已结束");

    }

}
