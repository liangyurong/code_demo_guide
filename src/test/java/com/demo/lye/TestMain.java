package com.demo.lye;

import com.demo.lyr.函数式编程.ExceptionUtil;
import org.junit.Test;
import org.springframework.util.StringUtils;

public class TestMain {


    @Test
    public void testFunction(){
        String str = "";
        ExceptionUtil.throwExceptionMessage(StringUtils.isEmpty(str)).throwExceptionMessage("字符串为空");
    }

}
