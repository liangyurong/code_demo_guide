package com.demo.lyr.nio_path;

import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathTest {
    @Test
    public void test(){
        // 文件路径
        String filePath = "src/main/java/com/demo/nio_path/aa.txt";

        // 创建Path实例
        Path path = Paths.get(filePath);
    }
}
