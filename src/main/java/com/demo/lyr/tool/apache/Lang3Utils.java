package com.demo.lyr.tool.apache;

import org.apache.commons.lang3.StringUtils;

public class Lang3Utils {


    public static void dd(){

        System.out.println(StringUtils.isEmpty(""));// true
        System.out.println(StringUtils.isEmpty(" "));// false（空格也占位置）
        System.out.println(StringUtils.isEmpty(null));// true

        System.out.println(StringUtils.isNotEmpty(""));// false
        System.out.println(StringUtils.isNotEmpty(" "));// true（空格也占位置）
        System.out.println(StringUtils.isNotEmpty(null));// false

        // 空串，单个空格，null，都是blank
        System.out.println(StringUtils.isBlank("")); // true
        System.out.println(StringUtils.isBlank(" "));// true
        System.out.println(StringUtils.isBlank(null));// true

        System.out.println(StringUtils.isNotBlank(""));// false
        System.out.println(StringUtils.isNotBlank(" "));// false
        System.out.println(StringUtils.isNotBlank(null));// false
    }

}
