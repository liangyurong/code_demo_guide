package com.demo.lyr.tool.map;

import org.springframework.util.CollectionUtils;

import java.util.Map;

/**
 * @author liangyurong
 *
 */
public class MapUtils {

    /**
     * 判断Map为null或者Map没有数据
     * @param map
     * @return
     */
    public Boolean isNullOrEmpty(Map<Object,Object> map){
        return CollectionUtils.isEmpty(map);
    }

}
