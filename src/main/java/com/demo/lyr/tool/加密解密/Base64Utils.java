package com.demo.lyr.tool.加密解密;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

public class Base64Utils {

    /**
     * byte[]数组 转 Base64字符串
     */
    private static String byte2Str(byte[] bytes){
        return DatatypeConverter.printBase64Binary(bytes);
    }

    /**
     * Base64字符串 转 byte[]数组
     */
    private static byte[] str2Byte(String str){
        return Base64.getDecoder().decode(str);
    }

}
