package com.demo.lyr.tool.加密解密;

import java.security.MessageDigest;

/**
 * MD5工具
 */
public class Md5Utils {

    /**
     * 将字符串进行MD5加密，生成32位md5码
     * @param inStr
     * @return
     */
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString()); //如果是部署到服务器，则使用log。
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 这是一个和MD5不同的加密解密算法。
     *
     * 操作：执行一次方法是加密，执行两次方法就解密。
     *
     * 加密：convertMD5(string password)
     * 解密：convertMD5(convertMD5(String password))
     */
    public static String convertMD5(String inStr) {

        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

}
