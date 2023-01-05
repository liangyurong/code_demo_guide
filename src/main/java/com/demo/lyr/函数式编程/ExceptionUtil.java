package com.demo.lyr.函数式编程;

public class ExceptionUtil {

    public static FunctionTest throwExceptionMessage(boolean flag){
        return msg -> {
            if(flag){
                throw new RuntimeException(msg);
            }
        };
    }

}
