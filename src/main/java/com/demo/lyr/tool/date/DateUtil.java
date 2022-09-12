package com.demo.lyr.tool.date;

import com.demo.lyr.tool.math_tool.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @description: 时间转换工具类
 * @create: 2020-06-29 14:10
 **/
@Slf4j
public class DateUtil {

    public static void main(String[] args) {

        System.out.println(getEndTimeOfCurrentDay(new Date()));
        System.out.println(getDayBegin(new Date()));
    }


    /**
     * 日期格式yyyy字符串常量
     */
    public static final String YEAR_FORMAT = "yyyy";
    /**
     * 日期格式MM字符串常量
     */
    public static final String ONLY_MONTH_FORMAT = "MM";
    /**
     * 日期格式dd字符串常量
     */
    public static final String DAY_FORMAT = "dd";
    /**
     * 日期格式yyyy-MM字符串常量
     */
    public static final String MONTH_FORMAT = "yyyy-MM";
    /**
     * 日期格式yyyyMM字符串常量
     */
    public static final String MONTH_FORMAT_SHORT = "yyyyMM";
    public static final String MONTH_FORMAT_SHORT_ONE = "yyMM";
    private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 日期格式yyyy-MM-dd字符串常量
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_SLASH = "yyyy/MM/dd";
    public static final String MONTH_DAY_FORMAT_SLASH = "MM/dd";
    public static final String MONTH_DAY_FORMAT_SLASH_EXTEND = "MM-dd";
    public static final String DATE_FORMAT_DIT = "yyyy.MM.dd";
    public static final String DATE_FORMAT_YEAR = "yyyy-01-01";
    public static final String DATE_FORMAT_MOUTH = "yyyy-MM-01";
    /**
     * 日期格式yyyyMMdd字符串常量
     */
    public static final String DATE_FORMAT_SHORT = "yyyyMMdd";
    /**
     * 日期格式yyyyMMddHHmm字符串常量
     */
    public static final String DATE_FORMAT_LONG = "yyyyMMddHHmm";
    public static final String DATE_FORMAT_MD = "MMdd";
    public static final String DATE_FORMAT_CHN_MD = "M月dd日";
    public static final String DATE_FORMAT_CHN= "yyyy年M月dd日";
    /**
     * 日期格式yyyy MM dd字符串常量
     */
    public static final String DATE_FORMAT_BANK = "yyyy MM dd";
    /**
     * 日期格式HH:mm:ss字符串常量
     */
    public static final String HOUR_FORMAT = "HH:mm:ss";
    /**
     * 日期格式yyyy-MM-dd HH:mm:ss字符串常量
     */
    public static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式yyyy-MM-dd'T'HH:mm:ss字符串常量
     */
    public static final String DATETIME_FORMAT_T = "yyyy-MM-dd'T'HH:mm:ss";


    public static final String DATETIME_FORMAT_T_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    /**
     * 日期格式yyyy-MM-dd 00:00:00字符串常量
     */
    public static final String DATETIME_FORMAT_ZERO = "yyyy-MM-dd 00:00:00";

    /**
     * 日期格式yyyy-MM-01 00:00:00字符串常量
     */
    public static final String DATETIME_FORMAT_MOUTH_ZERO = "yyyy-MM-01 00:00:00";


    /**
     * 日期格式yyyy-MM-dd 00:00:00字符串常量
     */
    public static final String DATETIME_FORMAT_HOUR = "yyyy-MM-dd HH:00:00";
    /**
     * 日期格式yyyy-MM-dd HH:mm:00字符串常量
     */
    public static final String DATETIME_FORMAT_MIN = "yyyy-MM-dd HH:mm:00";
    /**
     * 日期格式yyyy-MM-dd HH:mm字符串常量
     */
    public static final String DATETIME_FORMAT_MIN_WITHOUT_SECOND = "yyyy-MM-dd HH:mm";
    /**
     * 日期格式yyyy-MM-dd HH:mm:ss.SSS字符串常量
     */
    public static final String MILLI3SECOND_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss.SSS";
    /**
     * 日期格式yyyyMMddHHmmss字符串常量
     */
    public static final String yyyyMMddHHmmss_FORMAT = "yyyyMMddHHmmss";
    public static final String yyyyMMddHHmmssSSS_FORMAT = "yyyyMMddHHmmssSSS";
    /**
     * 时分秒分隔符
     */
    public static final String sep = ":";
    /**
     * 年月分隔符
     */
    public static final String dash = "-";
    /**
     * 空白
     */
    public static final String blank = " ";
    /**
     * 日期格式HH:mm字符串常量
     */
    public static final String HOUR_MIN_FORMAT = "HH:mm";
    /**
     * 日期格式HH:00字符串常量
     */
    public static final String HOUR_ZERO_FORMAT = "HH:00";
    /**
     * 当天0点
     */
    public static final String ZERO_POINT_OF_THE_DAY = "00:00";

    /**
     * 0点0分0秒
     */
    public static final String TIME_OF_ZERO = "00:00:00";
    /**
     * 当天最后时间
     */
    public static final String LAST_TIME_OF_THE_DAY = "23:59";

    private static final Long ONE_DAY_SECOND = 24 * 60 * 60L;
    private static final Long ONE_HOUR_SECOND = 60 * 60L;
    private static final Long ONE_MINUTE_SECOND = 60L;

    /**
     * date类型进行格式化输出（返回类型：String）
     *
     * @param date
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }

    /**
     * date类型输出为目标格式（返回类型：Date）
     *
     * @param date
     * @return
     */
    public static Date formatToDate(Date date, String pattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String dateStr = formatter.format(date);
        return parse(dateStr, pattern);
    }

    /**
     * 获得当天零时零分零秒
     *
     * @return
     */
    public static Date initDateByDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取未来 第 past 天的日期的结束时间
     *
     * @param past
     * @return
     */
    public static Date getFutureEndDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date date = calendar.getTime();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime endOfDay = localDateTime.with(LocalTime.MAX);
        return Date.from(endOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取未来 第 past 天的日期的开始时间
     *
     * @param past
     * @return
     */
    public static Date getFutureStartDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date date = calendar.getTime();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneId.systemDefault());
        LocalDateTime startOfDay = localDateTime.with(LocalTime.MIN);
        return Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 返回yyyy-MM-dd HH:mm:ss格式的日期字符串
     *
     * @param date
     * @return
     */
    public static String dateTimeStr(Date date) {
        return format(date, DATETIME_FORMAT);
    }

    /**
     * 返回yyyy-MM-dd 格式的日期字符串
     *
     * @param date
     * @return
     */
    public static String dateStr(Date date) {
        return format(date, DATE_FORMAT);
    }

    /**
     * 返回yyyy-MM-dd 格式的日期字符串
     *
     * @param date yyyy-MM-dd HH:mm:ss
     * @return Date yyyy-MM-dd
     */
    public static Date dateSub(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String dateStr = formatter.format(date);
        return parse(dateStr, DATE_FORMAT);
    }

    /**
     * 将"yyyy-MM-dd HH:mm:ss"型字符串转化为Date
     *
     * @param str
     * @return
     */
    public static Date parse(String str, String pattern) {
        if (StringUtils.isEmpty(str) || StringUtils.isEmpty(pattern)) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = formatter.parse(str);
        } catch (Exception e) {
            log.error(" 将yyyy-MM-dd HH:mm:ss型字符串转化为Date异常：", e);
        }
        return date;
    }

    /**
     * 将字符串转化为目标类型字符串
     *
     * @param str
     * @return
     */
    public static String parseToString(String str, String pattern, String tarPattern) {
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = formatter.parse(str);
        } catch (Exception e) {
            log.error("日期转换异常：", e);
        }
        return format(date, tarPattern);
    }

    /**
     * 日期加减秒
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addSec(Date date, int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //把日期往后增加一秒.整数往后推,负数往前移动
        calendar.add(Calendar.SECOND, num);
        //这个时间就是日期往后推一秒的结果
        return calendar.getTime();
    }

    /**
     * 日期加减分钟
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addMin(Date date, int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //把日期往后增加一分钟.整数往后推,负数往前移动
        calendar.add(Calendar.MINUTE, num);
        //这个时间就是日期往后推一分钟的结果
        return calendar.getTime();
    }

    /**
     * 日期加减小时
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addHour(Date date, int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //把日期往后增加一小时.整数往后推,负数往前移动
        calendar.add(Calendar.HOUR, num);
        //这个时间就是日期往后推一小时的结果
        return calendar.getTime();
    }

    /**
     * 日期加减天数
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addDate(Date date, int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //把日期往后增加一天.整数往后推,负数往前移动
        calendar.add(Calendar.DATE, num);
        //这个时间就是日期往后推一天的结果
        return calendar.getTime();
    }

    /**
     * 日期加减月份
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addMonths(Date date, int num) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //把日期往后增加n个月.整数往后推,负数往前移动
        calendar.add(Calendar.MONTH, num);
        //这个时间就是日期往后推n个月的结果
        return calendar.getTime();
    }

    /**
     * 日期加减周数
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addWeeks(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.WEEK_OF_YEAR, num);
        return calendar.getTime();
    }

    /**
     * 日期加减年份
     *
     * @param date
     * @param num
     * @return
     */
    public static Date addYears(Date date, int num) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, num);
        return calendar.getTime();
    }

    /**
     * 判断是否在该时间段内
     *
     * @return
     */
    public static boolean dateIsBetween(Date start, Date end, Date target) {
        return target.getTime() > start.getTime() && target.getTime() < end.getTime();
    }

    /**
     * 获取日期属于周几
     *
     * @return
     */
    public static int getDayOfWeek(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int i = cal.get(Calendar.DAY_OF_WEEK);
        return i == 1 ? 7 : i - 1;
    }

    /**
     * 获取星期几
     *
     * @param date
     * @return
     */
    public static String getDayNameOfWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        String[] arr = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        return arr[calendar.get(Calendar.DAY_OF_WEEK) - 1];
    }

    public static String getWeek(Date date) {
        String[] weeks = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }

    public static Integer getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    public static Integer getMonthOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    public static Integer getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.YEAR) + 1;
    }

    /**
     * 获取小时数
     */
    public static Float getHourNum() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        float hour = cal.get(Calendar.HOUR_OF_DAY);
        float min = cal.get(Calendar.MINUTE);
        return hour + min / 60;
    }

    /**
     * 获取小时数
     */
    public static Float getHourNum(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        float hour = cal.get(Calendar.HOUR_OF_DAY);
        float min = cal.get(Calendar.MINUTE);
        return hour + min / 60;
    }

    public static long getInterval(Date before, Date after) {
        long diff = after.getTime() - before.getTime();
        return diff / (1000 * 60 * 60 * 24);
    }

    public static String getDurationDesc(Long l) {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(l - 8 * 3600000);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        int second = cal.get(Calendar.SECOND);
        return hour + "小时" + minute + "分" + second + "秒";
    }

    /**
     * 获取某天的开始时间
     *
     * @param date
     * @return
     */
    public static Date getDayBegin(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定天的结束时间
     *
     * @return
     */
    public static Date getEndTimeOfCurrentDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.setTime(date);
        setMaxTimeOfDay(calendar);
        return calendar.getTime();
    }

    /**
     * 设置当天的结束时间
     *
     * @param calendar
     */
    private static void setMaxTimeOfDay(Calendar calendar) {
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.MILLISECOND, 999);
    }


    /**
     * 获取当前时间到凌晨12点的剩余毫秒数
     *
     * @return
     */
    public static Long getTheRestTime2NextDay(long nowTime) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return (cal.getTimeInMillis() - nowTime);
    }

    /**
     * 获取两个时间之间的所有日期
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Date> getBetweenDate(Date startDate, Date endDate) {
        List<Date> betweenDate = new ArrayList<>();
        //开始日期
        Calendar sCalendar = Calendar.getInstance();
        sCalendar.setTime(startDate);
        sCalendar.set(Calendar.MILLISECOND, 0);
        int year = sCalendar.get(Calendar.YEAR);
        int month = sCalendar.get(Calendar.MONTH);
        int day = sCalendar.get(Calendar.DAY_OF_MONTH);
        sCalendar.set(year, month, day, 0, 0, 0);
        //结束日期
        Calendar eCalendar = Calendar.getInstance();
        eCalendar.setTime(endDate);
        eCalendar.set(Calendar.MILLISECOND, 0);
        year = eCalendar.get(Calendar.YEAR);
        month = eCalendar.get(Calendar.MONTH);
        day = eCalendar.get(Calendar.DAY_OF_MONTH);
        eCalendar.set(year, month, day, 0, 0, 0);
        while (sCalendar.before(eCalendar)) {
            betweenDate.add(sCalendar.getTime());
            sCalendar.add(Calendar.DAY_OF_YEAR, 1);
        }
        betweenDate.add(eCalendar.getTime());
        return betweenDate;
    }

    /**
     * 获取两个时间之间的所有小时
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static List<String> getBetweenHours(Date startTime, Date endTime) {
        Date startDate = formatToDate(startTime, DATETIME_FORMAT_HOUR);
        Date endDate = formatToDate(endTime, DATETIME_FORMAT_HOUR);
        List<String> list = new ArrayList<>();
        while (startDate.getTime() <= endDate.getTime()) {
            list.add(format(startDate, DATETIME_FORMAT_HOUR));
            startDate = addHour(startDate, 1);
        }
        return list;
    }


    /**
     * 获取当前月的开始时间，结束时间
     *
     * @param date
     * @return
     */
    public static List<Date> getMonthStartAndEnd(Date date) {
        List<Date> dates = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDay = calendar.getTime();
        dates.add(firstDay);
        // 最后一天
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 0);
        Date lastDay = calendar.getTime();
        dates.add(lastDay);
        return dates;
    }

    public static String dateToDateTime(String dateStr, String pattern) {
        LocalDate date = LocalDate.parse(dateStr);
        LocalDateTime dateTime = date.atStartOfDay();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return dateTime.format(formatter);
    }

    /**
     * 获取同一天的两个时间的差（小时）
     *
     * @param startTime  开始时间 格式HH:mm
     * @param endTime    结束时间 格式HH:mm
     * @param recordDate 日期
     * @return 小时
     */
    public static double getDuration(Date recordDate, String startTime, String endTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        SimpleDateFormat timeFormat = new SimpleDateFormat(DateUtil.DATETIME_FORMAT);
        String date = dateFormat.format(recordDate);
        String startStr = date + " " + startTime + ":00";
        String endStr = date + " " + endTime + ":00";
        double duration = 0;
        try {
            Date startDate = timeFormat.parse(startStr);
            Date endDate = timeFormat.parse(endStr);
            duration = MathUtil.divideDouble((endDate.getTime() - startDate.getTime()), (1000 * 60 * 60), 2);
        } catch (Exception e) {
            log.error("获取同一天的两个时间的差异常：", e);
        }
        return duration;
    }

    /**
     * 计算两个日期之间相差的小时数（保留一位小数）
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static Double getDifferenceHour(Date startDate, Date endDate) {
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - startDate.getTime();
        long intValue = (int) (diff / 1000 / 60);
        BigDecimal b = new BigDecimal((double) intValue / 60);
        //第一个参数是保留小数的位数
        return b.setScale(1, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 获取两个时间的差（小时）
     *
     * @param startTime 开始时间 格式HH:mm:ss
     * @param endTime   结束时间 格式HH:mm:ss
     * @return 小时
     */
    public static double getDuration(String startTime, String endTime) {
        LocalTime start = LocalTime.parse(startTime);
        LocalTime end = LocalTime.parse(endTime);
        Duration between = Duration.between(start, end);
        long seconds = between.getSeconds();
        return MathUtil.divideDouble(seconds, (60 * 60), 2);
    }

    /**
     * 获取两个时间的差（小时）
     * [允许跨天,如果结束时间在开始时间之前，则认为跨天]
     *
     * @param startTime 开始时间 格式HH:mm
     * @param endTime   结束时间 格式HH:mm
     * @return 小时
     */
    public static double getDurationDaySpan(String startTime, String endTime) {
        LocalTime start = getLocalTimeByString(startTime);
        LocalTime end = getLocalTimeByString(endTime);
        long seconds;
        if (start.isBefore(end)) {
            Duration between = Duration.between(start, end);
            seconds = between.getSeconds();
        } else {
            Duration between1 = Duration.between(start, LocalTime.MAX);
            Duration between2 = Duration.between(LocalTime.MIN, end);
            seconds = between1.getSeconds() + between2.getSeconds();
        }
        return MathUtil.divideDouble(seconds, (60 * 60), 2);
    }

    private static LocalTime getLocalTimeByString(String time) {
        String[] times = time.split(":");
        return LocalTime.of(Integer.parseInt(times[0]), Integer.parseInt(times[1]));
    }


    /**
     * 时间戳按指定格式转化为日期（Date）
     *
     * @param timestamp
     * @param pattern
     * @return
     */
    public static Date convertTimestamp2Date(Long timestamp, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateStr = simpleDateFormat.format(new Date(timestamp));
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.parse(dateStr);
    }

    public static String convertToHours(Long mss, String symbol) {
        long hours = mss / (1000 * 60 * 60);
        long minutes = ((mss % (1000 * 60 * 60)) / (1000 * 60));
        String hoursStr = hours < 10 ? ("0" + hours) : Long.toString(hours);
        String minutesStr = minutes < 10 ? ("0" + minutes) : Long.toString(minutes);
        return hoursStr + symbol + minutesStr;
    }

    /**
     * 计算两个日期之间相差的天数（天）
     */
    public static Long getDaysBetweenDays(Date oldDate, Date newDate) {
        return (newDate.getTime() - oldDate.getTime()) / (1000L * 3600L * 24L);
    }

    /**
     * 7天前的0点
     *
     * @return
     */
    public static String getLast7DaysStartTime() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DAY_OF_MONTH, -7);
        LocalDateTime last7DaysStart = LocalDateTime.ofInstant(Instant.ofEpochMilli(cal.getTimeInMillis()), ZoneId.systemDefault());
        return last7DaysStart.format(dtf);
    }

    /**
     * 7天前的24点
     *
     * @return
     */
    public static String getLast7DaysEndTime() {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.add(Calendar.DAY_OF_MONTH, -7);
        LocalDateTime last7DaysEnd = LocalDateTime.ofInstant(Instant.ofEpochMilli(cal.getTimeInMillis()), ZoneId.systemDefault());
        return last7DaysEnd.format(dtf);
    }

    /**
     * 当前时间
     *
     * @return
     */
    public static String getCurrentTime() {
        LocalDateTime currentTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault());
        return currentTime.format(dtf);
    }


    /**
     * 获取指定日期之前或者之后的多少分钟
     *
     * @param date
     * @param minute
     * @return
     */
    public static String getTimeByMinute(Date date, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, minute);
        return new SimpleDateFormat(DATETIME_FORMAT).format(calendar.getTime());

    }

    /**
     * 获取两个时间相差的毫秒数
     */
    public static Long getTime(Date startTime, Date endTime) {
        long startDate = startTime.getTime();
        long endDate = endTime.getTime();
        //相差毫秒数
        return endDate - startDate;
    }

    /**
     * 获取两个时间差，然后自动转换为单位
     */
    public static String getTimeMinute(Date startTime, Date endTime) {
        long startDate = startTime.getTime();
        long endDate = endTime.getTime();
        long time = (endDate - startDate);
        double timeMinute = MathUtil.divideDouble((double) time, (double) (1000 * 60), 2);
        if (timeMinute < 0) {
            return 0.0 + "分钟";
        } else if (timeMinute >= (24 * 60)) {
            return MathUtil.divideDouble(timeMinute, 60 * 24, 2) + "天";
        } else if (timeMinute >= 60) {
            return MathUtil.divideDouble(timeMinute, 60, 2) + "小时";
        } else {
            return timeMinute + "分钟";
        }
    }

    /**
     * 获取两个时间差，返回格式：00:00:00
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static String getTimeDifference(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            return null;
        }
        LocalDateTime inDate = LocalDateTime.ofInstant(startDate.toInstant(), ZoneId.systemDefault());
        LocalDateTime nowDate = LocalDateTime.ofInstant(endDate.toInstant(), ZoneId.systemDefault());
        Duration duration = Duration.between(inDate, nowDate);
        log.info("时间差:" + duration.getSeconds());
        long hour = duration.getSeconds() / ChronoUnit.HOURS.getDuration().getSeconds();
        long minute = (duration.getSeconds() - ChronoUnit.HOURS.getDuration().getSeconds() * hour) / ChronoUnit.MINUTES.getDuration().getSeconds();
//        long second = (duration.getSeconds() - ChronoUnit.HOURS.getDuration().getSeconds() * hour) - minute * ChronoUnit.MINUTES.getDuration().getSeconds();
        return String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }

    /**
     * 将小时装换成格式：00:00
     *
     * @param hourValue
     * @return
     */
    public static String getTimeDifference(Double hourValue) {
        Long seconds = hourValue.longValue() * 3600;
        long hour = seconds / ChronoUnit.HOURS.getDuration().getSeconds();
        long minute = (seconds - ChronoUnit.HOURS.getDuration().getSeconds() * hour) / ChronoUnit.MINUTES.getDuration().getSeconds();
//        long second = (seconds - ChronoUnit.HOURS.getDuration().getSeconds() * hour) - minute * ChronoUnit.MINUTES.getDuration().getSeconds();
        return String.format("%02d", hour) + ":" + String.format("%02d", minute);
    }


    /**
     * 获取当月的天数
     */
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        return a.get(Calendar.DATE);
    }

    /**
     * 获取当天到指定几个月后的所有日期
     *
     * @param num
     * @return
     */
    public static List<String> getBetweenDateList(int num) {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, num);
        Date afterMonth = calendar.getTime();
        List<Date> betweenDate = DateUtil.getBetweenDate(date, afterMonth);
        List<String> list = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT);
        betweenDate.forEach(o -> list.add(dateFormat.format(o)));
        return list;
    }

    /**
     * 根据年份和月份获取当前月份下的所有天数列表
     * ["2021-12-01","2021-12-02",......"2021-12-31"]
     *
     * @param yearAndMonth 年月字符串，格式为：2021-12
     * @return
     */
    public static List<String> getDates(String yearAndMonth) {
        LocalDate date;
        if (StringUtils.isBlank(yearAndMonth)) {
            date = LocalDate.now();
        } else {
            String[] split = yearAndMonth.trim().split("-");
            date = LocalDate.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), 1);
        }
        LocalDate firstDay = date.with(TemporalAdjusters.firstDayOfMonth());
        LocalDate lastDay = date.with(TemporalAdjusters.lastDayOfMonth());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateUtil.DATE_FORMAT);
        return Stream.iterate(firstDay, day -> day.plusDays(1))
                .limit(ChronoUnit.DAYS.between(firstDay, lastDay) + 1)
                .map(o -> o.format(formatter)).collect(Collectors.toList());
    }


    /**
     * 开始时间和结束时间差并转成小时，保留两位小鼠
     *
     * @param startTime
     * @param endTime
     * @return
     */
    public static Double timeDifferenceHour(Date startTime, Date endTime) {
        if (startTime == null || endTime == null) {
            return 0.0;
        }
        long diff = endTime.getTime() - startTime.getTime();
        long hour = diff / (1000 * 60 * 60);
        return (double) hour;
    }


    /**
     * 获取两个时间的时间差
     *
     * @param endDate
     * @param nowDate
     * @return
     */
    public static Double getDatePoor(Date endDate, Date nowDate) {
        if (endDate == null || nowDate == null) {
            return 0.0;
        }
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少分钟
        long min = diff / (1000 * 60);
        return (double) min;
    }

    /**
     * 获取指定年月日时分秒的前一年最后一天的日期，并转成java.sql.Date
     *
     * @param currentDate
     * @return
     */

    public static java.sql.Date currentDateLastYearLastDay(Date currentDate) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(currentDate);
        c.add(Calendar.YEAR, -1);
        //拨回去年
        c.set(Calendar.DAY_OF_YEAR, c.getActualMaximum(Calendar.DAY_OF_YEAR));
        return new java.sql.Date(c.getTime().getTime());
    }

    /**
     * 获取指定年月日时分秒的前一年第一天的日期，并转成java.sql.Date
     *
     * @param currentDate
     * @return
     */
    public static java.sql.Date currentDateLastYearFirstDay(Date currentDate) {
        Calendar c = Calendar.getInstance(Locale.CHINA);
        c.setTime(currentDate);
        c.add(Calendar.YEAR, -1);
        //拨回去年
        c.set(Calendar.DAY_OF_YEAR, c.getActualMinimum(Calendar.DAY_OF_YEAR));
        return new java.sql.Date(c.getTime().getTime());
    }

    /**
     * 获取某个日期前一年的相同日期
     *
     * @param date
     * @return
     */
    public static Date getLastYearThisDate(Date date) {
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(date);
        //年份减1
        ca.add(Calendar.YEAR, -1);
        //结果
        return ca.getTime();
    }

    /**
     * 获取某个日期上个月的相同日期
     *
     * @param date
     * @return
     */
    public static Date getLastMonthThisDate(Date date) {
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(date);
        //年份减1
        ca.add(Calendar.MONTH, -1);
        //结果
        return ca.getTime();

    }

    /**
     * 获取指定月份天数
     *
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * java.util.Date转为java.sql.Date
     * 并且去掉时分秒毫秒
     *
     * @param date
     * @return
     */
    public static java.sql.Date convertJavaDateToSqlDate(Date date) {
        //得到一个Calendar的实例
        Calendar ca = Calendar.getInstance();
        //设置时间为当前时间
        ca.setTime(date);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        return new java.sql.Date(ca.getTime().getTime());
    }


    /**
     * 查询当前月份前12月份字符串
     *
     * @param time 支持 2017-01的时间字符串格式
     * @return
     */
    public static String[] getLast12Months(String time) {
        //处理月份输入条件
        if (time.length() == 7) {
            time = time + "-01 00:00:00";
        } else if (time.length() == 110) {
            time = time.substring(0, 7) + "-01 00:00:00";
        }
        Date date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            date = sdf.parse(time);
        } catch (Exception e) {
            return null;
        }

        String[] last12Months = new String[12];
        Calendar cal = Calendar.getInstance();
        //设置输入条件时间
        cal.setTime(date);
        //要先+1,才能把本月的算进去
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);
        for (int i = 0; i < 12; i++) {
            //逐次往前推1个月
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
            last12Months[11 - i] = cal.get(Calendar.YEAR) + "-" + addZeroForNum(String.valueOf(cal.get(Calendar.MONTH) + 1), 2);
        }

        return last12Months;
    }

    public static String addZeroForNum(String str, int strLength) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                // 左补0
                sb.append("0").append(str);
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * 获取当前月份的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayDateOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int last = cal.getActualMinimum(Calendar.DAY_OF_MONTH);
        cal.set(Calendar.DAY_OF_MONTH, last);
        return cal.getTime();
    }


    /**
     * 获取指定日期的年份
     */
    public static int getYearOfYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.getWeekYear();
    }

    /**
     * 获取指定日期的月份
     */
    public static int getMonthOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MONTH) + 1;
    }

    /**
     * 判断是否是对应的格式的日期字符串
     *
     * @param dateStr
     * @param datePattern
     * @return
     */
    public static boolean isRightDateStr(String dateStr, String datePattern) {
        DateFormat dateFormat = new SimpleDateFormat(datePattern);
        try {
            //采用严格的解析方式，防止类似 “2017-05-35” 类型的字符串通过
            dateFormat.setLenient(false);
            dateFormat.parse(dateStr);
            Date date = dateFormat.parse(dateStr);
            //重复比对一下，防止类似 “2017-5-15” 类型的字符串通过
            String newDateStr = dateFormat.format(date);
            if (dateStr.equals(newDateStr)) {
                return true;
            } else {
                //LOGGER.error("字符串dateStr:{}， 不是严格的 datePattern:{} 格式的字符串",dateStr,datePattern);
                return false;
            }
        } catch (ParseException e) {
            // LOGGER.error("字符串dateStr:{}，不能按照 datePattern:{} 样式转换",dateStr,datePattern);
            return false;
        }
    }


    /**
     * 两个 日期时间 相间隔多少天小时分钟
     *
     * @param endDate
     * @param nowDate
     * @return
     */
    public static String getDatePoorDayHourMin(Date endDate, Date nowDate) {
        if (endDate == null || nowDate == null) {
            return "";
        }
        try {
            long nd = 1000 * 24 * 60 * 60;
            long nh = 1000 * 60 * 60;
            long nm = 1000 * 60;
            // long ns = 1000;
            // 获得两个时间的毫秒时间差异
            long diff = endDate.getTime() - nowDate.getTime();
            // 计算差多少天
            long day = diff / nd;
            // 计算差多少小时
            long hour = diff % nd / nh;
            // 计算差多少分钟
            long min = diff % nd % nh / nm;
            // 计算差多少秒//输出结果
            // long sec = diff % nd % nh % nm / ns;
            return day + "天" + hour + "小时" + min + "分钟";
        } catch (Exception e) {
            return "";
        }
    }

    /**
     * 毫秒值转 天时
     *
     * @param dateDiff
     * @return
     */
    public static String longToStringDayHour(Long dateDiff) {
        StringBuilder sb = new StringBuilder();
        long day = dateDiff / ONE_DAY_SECOND;
        if (day > 0) {
            String strDay = day + "天";
            sb.append(strDay);
            dateDiff = dateDiff - day * ONE_DAY_SECOND;
        }
        long hour = dateDiff / ONE_HOUR_SECOND;
        if (hour > 0) {
            String strHour = hour + "小时";
            sb.append(strHour);
        } else {
            String strHour = "0小时";
            sb.append(strHour);
        }
        return sb.toString();
    }


    /**
     * 毫秒转天、小时(保留两位小数)
     *
     * @param millisecond
     * @return
     */
    public static String millisecondToString(long millisecond) {
        StringBuilder sb = new StringBuilder();
        long day = 1000 * 60 * 60 * 24L;
        if (millisecond > day) {
            long l = millisecond / day;
            sb.append(l).append("天");
            millisecond = millisecond % day;
        }
        Double hours = BigDecimal.valueOf(millisecond).divide(BigDecimal.valueOf(1000 * 60 * 60), 2, RoundingMode.HALF_UP).doubleValue();
        if (hours.compareTo(0.0) == 0) {
            sb.append("0小时");
            return sb.toString();
        }
        sb.append(hours).append("小时");
        return sb.toString();
    }


    /**
     * 获取两个日期之间相差的月份
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static List<String> getMonth(Date startDate, Date endDate) {
        List<String> list = new ArrayList<>();
        int year = DateUtil.getYear(endDate) - DateUtil.getYear(startDate);
        int month = DateUtil.getMonthOfYear(endDate) - DateUtil.getMonthOfYear(startDate) + year * 12;
        list.add(DateUtil.format(startDate, DateUtil.MILLI3SECOND_DATETIME_FORMAT));
        for (int i = 0; i < month; i++) {
            startDate = DateUtil.addMonths(startDate, 1);
            if (startDate.compareTo(endDate) <= 0) {
                list.add(DateUtil.format(startDate, MILLI3SECOND_DATETIME_FORMAT));
            }
        }
        list.add(DateUtil.format(endDate, DateUtil.MILLI3SECOND_DATETIME_FORMAT));
        return list;
    }


    /**
     * 转换日期格式为vue时间格式2021-11-21T12:30:00
     *
     * @param date
     * @return
     */
    public static String exchangeDate(String date) {
        LocalDate localDate1 = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return localDate1.atStartOfDay(ZoneId.of("America/New_York")).toOffsetDateTime().format(DateTimeFormatter.ofPattern(("yyyy-MM-dd'T'HH:mm:ss")));
    }

    /**
     * 字符转日期
     *
     * @param dateStr
     * @return
     */
    public static Date getDateByStr(String dateStr) {
        SimpleDateFormat formatter;
        if (dateStr == null) {
            return null;
        } else if (dateStr.length() == 7) {
            formatter = new SimpleDateFormat("yyyy-MM");
        } else if (dateStr.length() == 10) {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        } else if (dateStr.length() == 16) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        } else if (dateStr.length() == 19) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else if (dateStr.length() > 19) {
            dateStr = dateStr.substring(0, 19);
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            return null;
        }
        try {
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 日期减N天   接受时间类型：yyyy-mm-dd的字符串
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static String minusOneDay(String time, int num) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        //将接收的time中的年月日截取成String数组
        String[] timeStr = time.split("-");
        Calendar calendar = Calendar.getInstance();
        int year = Integer.valueOf(timeStr[0]);
        int month = Integer.valueOf(timeStr[1]);
        int day = Integer.valueOf(timeStr[2]);
        //判断time中的日期是否是该年该月的一号，如果不是就往前减一天;如果是就看情况减月份和年份
        if (day <= 1) {
            String date = null;
            //如果月份不是1月，就对月份减一；如果月份是1月，就对年份减一；
            if (month > 1) {
                month--;
                Calendar c = Calendar.getInstance();
                c.set(year, month, 0);
                Date parse = dateFormat.parse(year + "-" + month + "-" + c.get(Calendar.DAY_OF_MONTH));
                date = dateFormat.format(parse);
            } else if (month == 1) {
                year--;
                date = year + "-12-31";
            }
            return date;
        }
        //time中的日期不是该年该月的一号，直接往前减一天
        Date date = dateFormat.parse(time);
        calendar.setTime(date);
        calendar.add(Calendar.DATE, -num);
        return dateFormat.format(calendar.getTime());
    }


    /**
     * 给时间加N天  接受时间类型：yyyy-mm-dd的字符串
     *
     * @param time
     * @return
     * @throws ParseException
     */
    public static String addDay(String time, int num) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        String[] timeStr = time.split("-");
        Calendar calendar = Calendar.getInstance();
        int year = Integer.valueOf(timeStr[0]);
        int month = Integer.valueOf(timeStr[1]);
        int day = Integer.valueOf(timeStr[2]);
        calendar.set(year, month, 0);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        //判断time中的日期是否超过了该年该月的最后一天，如果超过就进行以下处理
        if (day >= dayOfMonth) {
            String date = null;
            //判断月份是否是12月，不是就往后加一个月；是的话就把年份加一年
            if (month < 12) {
                month++;
                Date parse = dateFormat.parse(year + "-" + month + "-01");
                date = dateFormat.format(parse);
            } else if (month == 12) {
                year++;
                date = year + "-01-01";
            }
            return date;
        }
        //time中的日期没有超过该年该月的最后一天，则天数往后加一天
        calendar.setTime(dateFormat.parse(time));
        calendar.add(Calendar.DATE, num);
        return dateFormat.format(calendar.getTime());
    }


    /**
     * 获取double列表中的最小值
     *
     * @param testList
     * @return
     */
    public static double ListDoubleArrayMin(List<Double> testList) {
        double minValue = testList.get(0);
        int minIndex = 0;
        for (int i = 0; i < testList.size(); i++) {
            if (i == testList.size() - 1) {
                continue;
            }
            double next = testList.get(i + 1);
            if (minValue > next) {
                minValue = next;
            }
        }
        return minValue;
    }

}

