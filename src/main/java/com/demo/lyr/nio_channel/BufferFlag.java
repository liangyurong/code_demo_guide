package com.demo.lyr.nio_channel;

import org.junit.Test;

import java.nio.ByteBuffer;

// 子缓冲区
public class BufferFlag {
    @Test
    public void flag(){
        //
        ByteBuffer  buffer = ByteBuffer.allocate(10);

        // 往缓冲区放入数据
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }

        // 创建子缓冲区
        buffer.position(3);
        buffer.limit(7);
        ByteBuffer slice = buffer.slice();

        // 改变子缓冲区的内容
        for (int i = 0; i < slice.capacity(); i++) {
            byte b = slice.get(i);
            b*=10; // 改变b的值
            slice.put(i,b);
        }

        buffer.position(0);
        buffer.limit(buffer.capacity());

        // 取出缓冲区数据
        while (buffer.remaining()>0){
            System.out.println(buffer.get());
        }


    }

}
