package com.demo.lyr.tool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;

/**
 * 路径获取相关工具类
 * @author liangyrong
 * @date 2022-10-01
 */
@Slf4j
public class PathUtils {

    /**
     * 获取启动包所在路径
     * @return
     */
    public static String getAbsolutePath(Class classes){
        ApplicationHome ah = new ApplicationHome(classes);
        return ah.getSource().getParentFile().toString();
    }

}
