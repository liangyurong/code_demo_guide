package com.demo.lyr.nio_buffer;

import org.junit.Test;

import java.nio.ByteBuffer;

public class OnlyReadBuffer {

    @Test
    public void readOnly(){
        ByteBuffer buffer = ByteBuffer.allocate(10);

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.put((byte)i);
        }

        // 创建只读缓冲区
        ByteBuffer readOnlyBuffer = buffer.asReadOnlyBuffer();

        // 改变原缓冲区的内容
        for (int i = 0; i < buffer.capacity(); i++) {
            byte b = buffer.get(i);
            b*=10; // 改变b的值
            buffer.put(i,b);
        }

        readOnlyBuffer.position(0);
        readOnlyBuffer.limit(buffer.capacity());

        // 取出只读缓冲区数据
        while (readOnlyBuffer.remaining()>0){
            System.out.println(readOnlyBuffer.get());
        }

    }

}
