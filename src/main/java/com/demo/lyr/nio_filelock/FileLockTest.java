package com.demo.lyr.nio_filelock;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileLockTest {

    @Test
    public void test() throws  Exception{

        // 缓冲区
        ByteBuffer buffer = ByteBuffer.wrap("这是数据".getBytes());

        // 文件路径
        String filePath = "src/main/java/com/demo/nio_filelock/aa.txt";

        // 创建channel
        Path path = Paths.get(filePath);
        FileChannel channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.APPEND);

        // ????
        channel.position(channel.size()-1);

        // 排他锁
        FileLock lock = channel.lock();
        System.out.println("是否共享锁："+lock.isShared());

        // 将内容写到缓冲区
        channel.write(buffer);
        channel.close();

        // 读取文件
        readFile(filePath);

    }

    public static void readFile(String filePath) throws  Exception {
        FileReader fileReader = new FileReader(filePath);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String str = bufferedReader.readLine();
        System.out.println("读取的内容================");

        while (str!=null){
            System.out.println(" "+str);
            str = bufferedReader.readLine();
        }
        fileReader.close();
        bufferedReader.close();
    }

}
