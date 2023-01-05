package com.demo.lyr.设计模式.适配器模式.音乐播放器.播放器;

// 已经存在的接口，这个接口需要适配
// 播放器，只能播放mp3格式
public class PlayerAdaptee {

    // 这个接口已经实现，但是只能播放mp3
    public void play(String type){
        if("mp3".equals(type)){
            System.out.println("正在播放：mp3");
        }else {
            System.out.println("无法播放：不支持的类型");
        }

    }

}
