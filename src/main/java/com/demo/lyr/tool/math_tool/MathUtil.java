package com.demo.lyr.tool.math_tool;

import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 数字处理工具类
 * @author:
 * @create:
 **/
public class MathUtil {

    public static Pattern IS_NUMERIC = Pattern.compile("[0-9]*");

    /**
     * 正则匹配是否是数字
     */
    public static Pattern IS_NUMBER = Pattern.compile("^-?\\d+(\\.\\d+)?$");

    /**
     * double类型保留指定位数
     *
     * @param d
     * @param scale
     * @return
     */
    public static double round(double d, int scale) {
        BigDecimal b = new BigDecimal(d);
        return b.setScale(scale, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 自动生成某个范围内Double类型的数
     *
     * @param min
     * @param max
     * @return
     */
    public static Double getRandom(int min, int max) {
        Double s = (Math.random() * (max - min + 1) + min);
        return s;
    }

    public static Integer getIntRandom(int min, int max) {
        Random random = new Random();
        int s = random.nextInt(max - min + 1) + min;
        return s;
    }

    /**
     * double类型保留指定位数
     *
     * @param d
     * @param scale
     * @return
     */
    public static float round(float d, int scale) {
        BigDecimal b = BigDecimal.valueOf(d);
        return b.setScale(scale, RoundingMode.HALF_UP).floatValue();
    }

    /**
     * double类型相除返回double
     *
     * @param a     除数
     * @param b     被除数
     * @param scale 保留几位小数
     * @return
     */
    public static Double divideDouble(double a, double b, int scale) {
        if(b == 0.0){
            return 1.0;
        }
        BigDecimal result = BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b),
                scale,
                RoundingMode.DOWN);
        return result.doubleValue();
    }

    /**
     * double类型相除返回double
     *
     * @param a     除数
     * @param b     被除数
     * @param scale 保留几位小数
     * @return
     */
    public static Double divideDouble(double a, double b, int scale,RoundingMode roundingMode) {
        BigDecimal result = BigDecimal.valueOf(a).divide(BigDecimal.valueOf(b),
                scale,
                roundingMode);
        return result.doubleValue();
    }

    /**
     * 判断字符串是否为数字
     *
     * @param
     * @return
     */
    public static boolean isNumeric(String str) {
        Pattern pattern = IS_NUMERIC;
        Matcher isNum = pattern.matcher(str);
        return isNum.matches();
    }

    /**
     * 提供精确的加法运算
     *
     * @param v1 被加数
     * @param v2 加数
     * @return 两个参数的和
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供精确的减法运算
     *
     * @param v1 被减数
     * @param v2 减数
     * @return 两个参数的差
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 是否是数字或小数
     *
     * @return boolean
     * @tags @return
     */
    public static boolean isNumber(String str) {
        if (StringUtils.isNotBlank(str)) {
            String reg = "\\d+(\\.\\d+)?";
            return str.matches(reg);
        }
        return false;
    }


    /**
     * 将浮点数转成字符串百分比：比如0.3转换为30%  0.355 转为 35.5%
     *
     * @param value
     * @return
     */
    public static String doubleToPercent(Double value) {
        if (value == null) {
            return "0%";
        }
        DecimalFormat df = new DecimalFormat("#.##%");
        return df.format(value);

    }

    /**
     * 两个Double类型的数相乘，已避免精度问题(保留2位数)
     *
     * @param a
     * @param b
     * @return
     */
    public static double mulToKeepTwoDecimalPlaces(double a, double b) {
        BigDecimal d1 = new BigDecimal(Double.toString(a));
        BigDecimal d2 = new BigDecimal(Double.toString(b));
        //保留2位数
        return d1.multiply(d2).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 两个Double类型的数相乘，已避免精度问题(不保留小数)
     *
     * @param a
     * @param b
     * @return
     */
    public static double mul(double a, double b) {
        BigDecimal d1 = new BigDecimal(Double.toString(a));
        BigDecimal d2 = new BigDecimal(Double.toString(b));
        return d1.multiply(d2).doubleValue();
    }

    /**
     * 三个Double类型的数相乘，已避免精度问题
     *
     * @param a
     * @param b
     * @param c
     * @return
     */
    public static double mulDouble(double a, double b, double c) {
        BigDecimal d1 = new BigDecimal(Double.toString(a));
        BigDecimal d2 = new BigDecimal(Double.toString(b));
        BigDecimal d3 = new BigDecimal(Double.toString(c));
        //保留2位数
        return d1.multiply(d2).multiply(d3).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    /**
     * 判断是否是数字、负数
     *
     * @param value
     * @return
     */
    public static boolean isNumberBy(String value) {
        Pattern pattern = IS_NUMBER;
        if (value == null) {
            return false;
        }
        return pattern.matcher(value).matches();

    }

}

