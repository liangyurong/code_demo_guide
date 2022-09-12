package com.demo.lyr.design_patterns.适配器模式.版本迭代;

import java.util.List;
import java.util.Map;

// 适配器
public class ListAdapter implements ClientTarget {

    private MapAdaptee mapAdaptee;

    public ListAdapter(MapAdaptee mapAdaptee){
        this.mapAdaptee = mapAdaptee;
    }

    @Override
    public void soutObject(Object obj) {
        if(obj instanceof Map){
            mapAdaptee.soutMap((Map)obj);
        }else if(obj instanceof List){
            for (Object ele : ((List) obj)) {
                System.out.println(ele);
            }
        }
    }
}
