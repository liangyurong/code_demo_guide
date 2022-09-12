package com.demo.lyr.design_patterns.适配器模式.音乐播放器;

import com.demo.lyr.design_patterns.适配器模式.音乐播放器.播放器.PlayerAdaptee;
import com.demo.lyr.design_patterns.适配器模式.音乐播放器.适配器.PlayerAdapter;

public class MusicClient {
    public static void main(String[] args) {

        // 创建需要被适配的对象
        PlayerAdaptee adaptee = new PlayerAdaptee();

        // 适配器
        PlayerAdapter adapter = new PlayerAdapter(adaptee);

        adapter.translate("mp3r");
    }
}
