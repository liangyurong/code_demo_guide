package com.demo.lyr.tool.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author liangyurong
 *
 */
public class DateUtils {
    public static void main(String[] args) throws InterruptedException {
        Date date1 = new Date();
        Thread.sleep(10);
        Date date2 = new Date();
        System.out.println(isAfter(date1,date2));
        System.out.println(isBefore(date1,date2));

        System.out.println(getTodayStr());
        System.out.println(getYesterdayStr());

    }


    /**
     * 返回今天 , String类型 , yyyy-MM-dd
     * @return
     */
    public static String getTodayStr(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(new Date());
        return today;
    }

    /**
     * 返回昨天 , String类型 , yyyy-MM-dd
     * @return
     */
    public static String getYesterdayStr(){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String today = df.format(getYesterdayDate());
        return today;
    }

    /**
     * 返回昨天 ， Date类型
     * @return
     */
    public static Date getYesterdayDate(){
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        c.add(Calendar.DAY_OF_MONTH, -1);
        Date yesterday = c.getTime();
        return yesterday;
    }

    /**
     * Date转为String
     * @param date
     * @return
     */
    public static String date2Str(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * date1: 2022-08-12 00:00:00
     * date2: 2022-08-22 00:00:00
     * isAfter(date1,date2) : false
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isAfter(Date date1,Date date2){
        return date1.after(date2);
    }

    /**
     * date1: 2022-08-12 00:00:00
     * date2: 2022-08-22 00:00:00
     * isBefore(date1,date2) : true
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isBefore(Date date1,Date date2){
        return date1.before(date2);
    }


}
