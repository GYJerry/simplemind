package cn.simplemind.jerry.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 日期操作工具类
 * @author wuyingdui
 * @date   2017年5月16日 下午5:17:40
 */
public class DateUtil {
    
    /**
     * 限制该类的静态方法只能通过类名调用，不能进行实例化
     * @throws Exception
     */
    private DateUtil() throws Exception {
        throw new Exception("please do not initialiize me!");
    }
    
    public static final String TIMEF_FORMAT = "yyyy-MM-dd HH:mm:ss";
    
    public static final String ALIPAY_NOTIFY_DATETIME_FROMAT = "yyyy-MM-dd+HH:mm:ss";

    public static final String ZHCN_DATE_FORMAT = "yyyy年MM月dd日";

    public static final String DATE_SS_FORMAT = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_MM_FORMAT = "yyyy/MM/dd HH:mm";

    public static final String DATE_DD_FORMAT = "yyyyMMdd";

    public static final String DATE_MMDD_FORMAT = "yyyy-MM-dd";

    public static final String DATE_YYMMDD_FORMAT = "yyyy/MM/dd";

    public static final String DATE_MM_FORMAT2 = "yyyy-MM-dd HH:mm";

    public static final String DATE_HH_MM_FORMAT = "HH:mm";
    
    public static final String DATE_YYYYMMDDHHMMSS_FORMAT = "yyyyMMddHHmmss";

    // 注意HH与hh含认不同，HH表示以24小时制取，hh表示以12小时制取
    // 常用的格式的含义，摘自Jdk，注意大小写的含义通常是不同的：
    // 字母 含义 示例
    // y Year 1996;96 哪一年
    // M Month in year J uly;Jul;07 一年中的哪一月
    // m Minute in hour 30 一个小时中的第几分钟
    // w Week in year 27 一年中的第几个星期
    // W Week in month 2 一个月中的第几个星期
    // D Day in year 189 一年中的第几天
    // d Day in month 10 一个月中的第几天
    // H Hour in day (0-23) 0 一天中的第几个小时（24小时制）
    // h Hour in am/pm (1-12) 12 一天中上午、下午的第几个小时（12小时制）
    // S Millisecond 978 毫秒数
    // s Second in minute 55 一分钟的第几秒
    
    private static GregorianCalendar gc = null;

    static
    {
        gc = new GregorianCalendar(Locale.CHINA);
        gc.setLenient(true);
        gc.setFirstDayOfWeek(Calendar.MONDAY);
    }
    
    /**
     * 获取毫秒。
     *
     * @return
     */
    public static long getMilliseconds()
    {
        return (new Date()).getTime();
    }

    /**
     * 将指定时间值转换为日期
     * 
     * @author wuyingdui
     * @date   2017年5月31日 下午5:13:59
     * @param  objDate 指定值，可以是时间字符串、整型数等
     * @param  formats 指定时间值格式，为空时默认使用“yyyy-MM-dd HH:mm:ss”格式
     * @return
     * @throws ParseException 
     */
    public static Date strToDate(Object objDate, String[] formats)
    {
        if (objDate == null) {
            throw new IllegalArgumentException("Date must not be null");
        }
        
        String strDate = String.valueOf(objDate);
        Date date = null;
        // 设置默认格式化形式
        if (formats == null || formats.length == 0) {
            formats = new String[]{TIMEF_FORMAT};
        }
        
        SimpleDateFormat parser = null;
        ParsePosition pos = new ParsePosition(0);
        for (int i = 0; i < formats.length; i++) {
            if (i == 0) {
                parser = new SimpleDateFormat(formats[0]);
            } else {
                parser.applyPattern(formats[i]);
            }
            pos.setIndex(0);
            date = parser.parse(strDate, pos);
            if (date != null && pos.getIndex() == strDate.length()) {
                return date;
            }
        }
        
        // 如果上述步骤没有完成转换，则按照整型数进行判断
        Long timeStamp = Long.parseLong(strDate);
        date = new Date(timeStamp);
        return date;
    }

    public static String getFormatNowDate()
    {
        Date nowTime = new Date(System.currentTimeMillis());
        SimpleDateFormat sdFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String retStrFormatNowDate = sdFormatter.format(nowTime);
        return retStrFormatNowDate;
    }

    /**
     * 时间字符串转换为日期
     */
    public static Date strToDate2(String strDate, String format, String split)
    {
        String[] strs = strDate.split(split);
        String year = "";
        String month = "";
        String day = "";
        if (strs != null && strs.length > 2)
        {
            year = strs[0];
            month = strs[1];
            day = strs[2];
            if (Integer.parseInt(strs[1]) < 10)
            {
                month = "0" + strs[1];
            }
            if (Integer.parseInt(strs[2]) < 10)
            {
                day = "0" + strs[2];
            }
            strDate = year + split + month + split + day;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        ParsePosition pos = new ParsePosition(0);
        Date data = formatter.parse(strDate, pos);

        return data;
    }

    /**
     * 整型秒转化为时间date对象。
     *
     * @param ltime
     * @return
     */
    public static Date long2Date(long ltime)
    {
        return new Date(ltime * 1000);
    }

    /**
     * 整型秒转化为指定格式的时间字符串。
     *
     * @param ltime
     * @param format
     * @return
     */
    public static String long2Date(long ltime, String format)
    {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // 前面的lSysTime是秒数，先乘1000得到毫秒数，再转为java.util.Date类型
        Date d = new Date(ltime * 1000);
        return sdf.format(d); // 得到精确到秒的表示：08/31/2006 21:08:00
    }

    /**
     * 时间戳转化为日期
     * timeStamp 毫秒
     */
    public static Date timeStampToDate(long timeStamp)
    {
        SimpleDateFormat format = new SimpleDateFormat(TIMEF_FORMAT);
        String d = format.format(timeStamp); // 毫秒
        Date date;
        try
        {
            date = format.parse(d);
        }
        catch (ParseException e)
        {
            date = new Date();
        }

        return date;
    }

    /**
     * 获取日期d的years年后的一个Date
     * @param d
     * @param years 负数为前years年
     * @return Date
     */
    public static Date getInternalDateByYear(Date d, int years)
    {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.YEAR, years);
        return now.getTime();
    }

    /**
     * 获取日期d的months月后的一个Date
     * @param d
     * @param months
     * @return Date
     */
    public static Date getInternalDateByMon(Date d, int months)
    {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.MONTH, months);
        return now.getTime();
    }

    /**
     * 获取日期d的days天后的一个Date
     *
     * @param d
     * @param days
     * @return Date
     */
    public static Date getInternalDateByDay(Date d, int days)
    {
        Calendar now = Calendar.getInstance(TimeZone.getDefault());
        now.setTime(d);
        now.add(Calendar.DATE, days);
        return now.getTime();
    }

    /**
     * 获取制定Date的hours小时后的Date
     * @param d
     * @param hours
     * @return
     */
    public static Date getInternalDateByHour(Date d, int hours)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(d);
        calendar.add(Calendar.HOUR, hours);
        return calendar.getTime();
    }

    /**
     * 获取指定Date的minutes分钟后的Date
     * @param d
     * @param minutes
     * @return
     */
    public static Date getInternalDateByMinutes(Date d, int minutes)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(d);
        calendar.add(Calendar.MINUTE, minutes);
        return calendar.getTime();
    }

    /**
     * 获取制定Date的seconds秒后的Date
     * @param d
     * @param seconds
     * @return
     */
    public static Date getInternalDateBySeconds(Date d, int seconds)
    {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(d);
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 得到指定时间月份后（前）若干月的第一天
     * @author chenpx
     * @param months  相隔的月份
     * @return
     */
    public static String getAfterMonthFirstDay(Date d, int months)
    {
        Date newdate = getInternalDateByMon(d, months);
        return dateToStr(newdate, "yyyyMM") + "01";
    }

    /** */

    /**
     * date本周 周一到 周日日期
     * @return
     */
    public static Date[] getBeginAndEndOfWeeks(Date date)
    {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        Date[] dates = new Date[7];
        int count = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == count)
        {
            cal.add(Calendar.DAY_OF_YEAR, -6);
        }
        else
        {
            cal.add(Calendar.DAY_OF_YEAR, -(count - 2));
        }
        count = 0;
        while (count < 7)
        {
            dates[count] = cal.getTime();
            // 日期往后推一天
            cal.add(Calendar.DAY_OF_YEAR, 1);
            count++;
        }
        return dates;
    }

    /** */

    /**
     * date 的第index周之后的周一到周日的日期
     */
    public static Date[] getBeginAndEndOfWeekAfter(Date date, int index)
    {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, index);
        return getBeginAndEndOfWeeks(cal.getTime());
    }

    /**
     * 取得日期所在月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstMonthDay(Date date)
    {
        initCalendar(date);
        int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
        gc.add(Calendar.DAY_OF_MONTH, (1 - dayOfMonth));
        return gc.getTime();
    }

    /**
     * 取得日期所在月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastMonthDay(Date date)
    {
        initCalendar(date);
        int dayOfMonth = gc.get(Calendar.DAY_OF_MONTH);
        int maxDaysOfMonth = gc.getActualMaximum(Calendar.DAY_OF_MONTH);
        gc.add(Calendar.DAY_OF_MONTH, (maxDaysOfMonth - dayOfMonth));
        return gc.getTime();
    }

    private static void initCalendar(Date date)
    {
        if (date == null)
        {
            throw new IllegalArgumentException("argument date must be not null");
        }

        gc.clear();
        gc.setTime(date);
    }

    /**
     * 得到指定日期是星期几
     *
     * @param dateTime
     * @param dateFormat
     * @return
     */
    public static int getWeek(Date dateTime)
    {
        GregorianCalendar gcd = new GregorianCalendar(Locale.CHINA);
        gcd.setLenient(true);
        gcd.setFirstDayOfWeek(Calendar.MONDAY);
        gcd.clear();
        gcd.setTime(dateTime);
        int dayOfWeek = gcd.get(Calendar.DAY_OF_WEEK);
        return dayOfWeek;
    }

    /**
     * 计算给出的日期为星期几来判断是否是工作日,true:节假日，false：工作日
     * @author chenpx
     * @param date 2015-03-12
     * @return
     * @throws ParseException 
     */
    public static boolean IfWeekend(String dateStr) throws ParseException
    {
        Date date = strToDate(dateStr, new String[]{DATE_MMDD_FORMAT});
        int number = getWeek(date);//星期表示1-7，是从星期日开始，
        if (number == 1 || number == 7)
        {
            return true;
        }
        return false;
    }

    /**
     * 返回比当前日期晚几分钟的一个yyyy-MM-dd HH:mm:ss的日期串晚的分钟数可由输入参数minute控制
     * 返回date
     * @param minute
     * @return 返回延迟N分钟后的时间串
     */
    public static Date getCurrentNextMinuteDate(int minute)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        cal.add(Calendar.MINUTE, minute);
        return cal.getTime();
    }

    /**
     * 返回比当前日期晚几分钟的一个yyyy-MM-dd HH:mm:ss的日期串晚的分钟数可由输入参数minute控制
     *
     * @param minute
     * @return 返回延迟N分钟后的时间串
     */
    public static String getCurrentNextMinute(int minute)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.util.Date());
        cal.add(Calendar.MINUTE, minute);
        DateFormat sdf = new SimpleDateFormat(TIMEF_FORMAT);
        return sdf.format(cal.getTime());
    }
    
    /**
     * 验证日期格式是否为指定格式
     * @author wuyingdui
     * @param  dateStr  待验证的日期字符串
     * @param  format   指定的日期格式，可为本类中的静态变量指定的日期格式
     * @return 是否通过校验
     */
    public static boolean isValidDate(String dateStr, String format)
    {
        boolean passValid = true;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            simpleDateFormat.setLenient(false);
            simpleDateFormat.parse(dateStr);
        } catch (ParseException e) {
            // 如果throw java.text.ParseException或者NullPointerException，就说明格式不对
            // System.out.println(e);
            passValid = false;
        } 
        return passValid;
    }
    
    /**
     * @Description: 根据当前时间获取上周一的开始时间：00:00:00
     * @param date
     * @return: Date
     * @author xinxing_huang
     */
    public static Date getLastWeekMonday(Date date) 
    {    
       if(date == null)
       {
           date = new Date();
       }
       
       Calendar cal = Calendar.getInstance();    
       cal.setTime(date);
       cal.add(Calendar.DATE, -1);
       cal.add(Calendar.WEEK_OF_YEAR, -1);// 一周    
       cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
       cal.set(Calendar.HOUR_OF_DAY, 0);
       cal.set(Calendar.MINUTE,0);
       cal.set(Calendar.SECOND,0);              
      return cal.getTime(); 
    }
    
    /**
     * @Description:根据当前时间 获取上周日的结束时间：23:59:59
     * @param date
     * @return: Date
     * @author xinxing_huang
     */
    public static Date getLastWeekSunday(Date date) 
    {    
        if(date == null)
        {
            date = new Date();
        }
        
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.add(Calendar.DATE, -1);
        cal.set(Calendar.DAY_OF_WEEK, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE,59);
        cal.set(Calendar.SECOND,59);
        
        return cal.getTime();    
    }
    
    /**
     * 根据当前时间获取本周一的开始时间：00:00:00
     * @author wuyingdui
     * @date   2016年12月13日 下午7:09:24 
     * @param  date
     * @return
     */
    public static Date getThisWeekMonday(Date date) 
    {    
        if(date == null)
        {
            date = new Date();
        }
           
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);    
        cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0); 
        
        return cal.getTime(); 
    }
    
    /**
     * 根据当前时间 获取本周日的结束时间：23:59:59
     * @author wuyingdui
     * @date   2016年12月13日 下午7:09:55 
     * @param  date
     * @return
     */
    public static Date getThisWeekSunday(Date date) 
    {    
        if(date == null)
        {
            date = new Date();
        }
        
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        // 设置成下星期，因为系统中本周日是统计在下周中的
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        
        return cal.getTime();
    }
    
    /**
     * 日期转换为时间字符串，如yyyy/MM/dd HH:mm:ss
     * @author wuyingdui
     * @date   2016年12月14日 下午1:51:26 
     * @param  date
     * @param  format
     * @return
     */
    public static String dateToStr(Date date, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String strDate = formatter.format(date);

        return strDate;
    }
    
    /**
     * 获取指定时间所在“年 + 周”
     * @author wuyingdui
     * @date   2016年12月14日 下午7:25:09 
     * @param date
     * @param n
     * @return
     */
    public static String getWeek(Date date, int n)
    {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        now.add(Calendar.DATE, -1);
        // 获取当前为星期几
        int d = now.get(Calendar.DAY_OF_WEEK);
        // 如果是周日，则减一，否则周日为下一周的时间
        if(d == Calendar.SUNDAY)
        {
            now.add(Calendar.DATE, -1);
        }
        // 获取当周所属前年份
        int year = now.getWeekYear();
        
        int weekOfYear = now.get(Calendar.WEEK_OF_YEAR);
        
        return year + weekOfYear + "";
    }
    
    /**
     * 获取指定时间对应的当周周一到周日对应的时间段字符串
     * @author wuyingdui
     * @date   2016年12月14日 下午7:33:45 
     * @param  date
     * @param  n
     * @return
     */
    public static String getWeekStartAndEndStr(Date date, int n)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获取指定时间的前n周或后n周对应的时间
        cal.add(Calendar.DATE, 7*n);
        Date monday = getThisWeekMonday(cal.getTime());
        Date sunday = getThisWeekSunday(cal.getTime());
        String str = dateToStr(monday,"yyyy.MM.dd") + " - " + dateToStr(sunday,"yyyy.MM.dd");
        
        return str;
    }
    
    /**
     * 获取指定日期的起始时间
     * @author wuyingdui
     * @date   2017年1月13日 上午8:17:34 
     * @param  date
     * @return
     */
    public static Date getDateStartTime(Date date)
    {
        if(date == null)
        {
            date = new Date();
        }
           
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        
        return cal.getTime();
    }
    
    /**
     * 获取指定日期的结束时间
     * @author wuyingdui
     * @date   2017年1月13日 上午8:17:10 
     * @param  date
     * @return
     */
    public static Date getDateEndTime(Date date)
    {
        if(date == null)
        {
            date = new Date();
        }
        
        Calendar cal = Calendar.getInstance();    
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        
        return cal.getTime();
    }
    
    /**
     * 获取指定日期的前几天或后几天
     * @author wuyingdui
     * @date   2017年1月13日 上午8:16:24 
     * @param  date
     * @param  n
     * @return
     */
    public static Date getSpecifyDate(Date date, int n)
    {
        if(date == null)
        {
            date = new Date();
        }
        
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        
        return cal.getTime();
    }


    /**
     * 获取当前日期前n个月的开始和结束时间
     * 
     * @author liuya
     * @date 2017/3/30 20:18
     * @param date
     * @return
     */
    public static Date[] getMonthStartEndTime(Date date, int n) {
        if (date == null) {
            date = new Date();
        }

        Date[] dateRange = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.add(Calendar.MONTH, -n);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        dateRange[0] = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        dateRange[1] = cal.getTime();

        return dateRange;
    }
    
    public static void main(String[] args) throws ParseException {
        Date date = strToDate("2015-03-04 11:15:25", new String[]{});
        System.out.println(date);
    }
}
