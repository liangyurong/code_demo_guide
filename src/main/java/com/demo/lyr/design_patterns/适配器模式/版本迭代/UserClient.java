package com.demo.lyr.design_patterns.适配器模式.版本迭代;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserClient {
    public static void main(String[] args) {

        // 版本1
        MapAdaptee mapAdaptee = new MapAdaptee();
        Map<String,Object>  map = new HashMap<>();
        map.put("1","v1");
        map.put("2","v2");
        mapAdaptee.soutMap(map);

        // 版本2：新增对List参数的支持
        ArrayList<Object> list = new ArrayList<>();
        list.add("1");
        list.add("3");
        list.add("5");
        list.add("4");
        list.add("2");
        ListAdapter listAdapter = new ListAdapter(mapAdaptee);
        listAdapter.soutObject(list);

        listAdapter.soutObject(map);

    }
}
