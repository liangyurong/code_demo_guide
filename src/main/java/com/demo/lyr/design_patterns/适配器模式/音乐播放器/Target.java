package com.demo.lyr.design_patterns.适配器模式.音乐播放器;

// 定义客户端使用的接口，与特定领域相关
public interface Target {
    // 客户端请求处理的方法
    void translate(String type);
}
