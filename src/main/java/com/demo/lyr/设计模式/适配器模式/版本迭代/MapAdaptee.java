package com.demo.lyr.设计模式.适配器模式.版本迭代;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MapAdaptee {

    // 前提：已存在的方法，不方便修改和删除。因此才需要适配器
    public void soutMap(Map<String,Object> map){
        Set<String> keySet = map.keySet();
        Iterator<String> it = keySet.iterator();
        while (it.hasNext()){
            String key = it.next();
            System.out.println(key+" : "+ map.get(key));
        }
    }

}
