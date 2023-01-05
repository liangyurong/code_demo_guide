package com.demo.lyr.tool.互转;

/**
 * 下划线与驼峰互转
 */
public class LineAndHumpUtils {
    //驼峰转下划线
    public static String Hump2Line(String str) {
        StringBuilder buf = new StringBuilder();
        for (int i = 0,len=str.length(); i < len; ++i) {
            char ch = str.charAt(i);
            if (ch >= 'A' && ch <= 'Z') {
                char ch_ucase = (char) (ch + 32);
                if (i > 0) {
                    buf.append('_');
                }
                buf.append(ch_ucase);
            } else {
                buf.append(ch);
            }
        }
        return buf.toString();
    }

    //下划线转驼峰
    public static String line2Hump(String str){

        if (str == null) {
            return null;
        }
        String[] split = str.split("_");
        StringBuffer sb = new StringBuffer(str.length());
        for (String s : split) {
            char[] chars = s.toCharArray();
            if(chars[0] >= 'A' && chars[0] <='Z'){

            }else if(chars[0] >='a' && chars[0] <= 'z'){
                chars[0] -=32;
            }

            sb.append(chars);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(Hump2Line("myEmail"));
        System.out.println(line2Hump("my_email"));
    }
}
