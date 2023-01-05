package com.demo.lyr.tool.身份证;

/**
 * 身份证工具类
 */
public class IdCardUtils {

    // 身份证号的长度是15
    private static final int idCardLen15 = 15;
    // 身份证号的长度是18
    private static final int idCardLen18 = 18;

    /**
     * - 公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
     * - 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。顺序码的奇数分给男性，偶数分给女性。校验码是根据前面十七位数字码，按照ISO 7064:1983.MOD 11-2校验码计算出来的检验码
     */

    /**
     * 从身份证号中判断性别: 1 man ， 0 woman ， -1 不符合规则
     * 只考虑中国大陆的身份证,身份证号只有15位和18位
     */
    public static Integer judgeSexFromIdNum(String idNum){
        int len = idNum.trim().length();

        if(len == idCardLen15){
            return "1".equals(idNum.substring(13, 14)) ? 1 : 0 ;
        }
        if(len == idCardLen18){
            return "1".equals(idNum.substring(16, 17)) ? 1 : 0;
        }
        return -1;
    }

    // 根据18位身份证号计算年龄


    // 根据15位身份证号计算年龄

}
