package com.demo.lyr.设计模式.适配器模式.音乐播放器.适配器;

import com.demo.lyr.设计模式.适配器模式.音乐播放器.Target;
import com.demo.lyr.设计模式.适配器模式.音乐播放器.播放器.PlayerAdaptee;

public class PlayerAdapter implements Target {

    // 持有需要被适配的对象
    private PlayerAdaptee adaptee;

    public PlayerAdapter(PlayerAdaptee adaptee){
        this.adaptee = adaptee;
    }

    @Override
    public void translate(String type) {
        // 转调已经实现了的方法，进行适配
        if("mp3".equals(type)){
            adaptee.play(type);
        }else {
            System.out.println("正在播放："+type);
        }
    }
}
