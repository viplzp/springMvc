package com.lzp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**   
 * 版权所有：2014-Hisuntech.Co.Ltd All rights reserved.  
 * 项目名称：zbd-web   
 *
 * 类描述：日期时间工具类
 * 类名称：com.hponline.util.DateUtils     
 * 创建人：GuoqingCao
 * 创建时间：2014年8月14日 下午12:50:21   
 * 修改人：
 * 修改时间：2014年8月14日 下午12:50:21   
 * 修改备注：   
 * 版本：   V1.0 
 */

public class DateUtils {
    private static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final SimpleDateFormat datetimeFormat2 = new SimpleDateFormat("yyyyMMddHHmmss");

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    private static SimpleDateFormat simpledateFormat = new SimpleDateFormat("yyyyMMdd");
    
    private static Calendar calS=Calendar.getInstance();   
    private static Pattern   p   =   Pattern.compile("\\d{4}-\\d{2}-\\d{2}");//定义整则表达式   
   
    
    /** 
     * 将String型格式化,比如想要将2011-11-11格式化成2011年11月11日,就StringPattern("2011-11-11","yyyy-MM-dd","yyyy年MM月dd日").
     * @param date String 想要格式化的日期
     * @param oldPattern String 想要格式化的日期的现有格式
     * @param newPattern String 想要格式化成什么格式
     * @return String 
     */ 
    public static String StringPattern(String date, String oldPattern, String newPattern) { 
        if (date == null || oldPattern == null || newPattern == null) 
            return ""; 
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldPattern) ;        // 实例化模板对象  
        SimpleDateFormat sdf2 = new SimpleDateFormat(newPattern) ;        // 实例化模板对象  
        Date d = null ;  
        try{  
            d = sdf1.parse(date) ;   // 将给定的字符串中的日期提取出来  
        }catch(Exception e){            // 如果提供的字符串格式有错误，则进行异常处理  
            e.printStackTrace() ;       // 打印异常信息  
        }  
        return sdf2.format(d);
    } 
    
    /**  
     * 计算剩余时间  
     * 有bug 2015-12-31计息，2015-04-30到期 ，2015-03-01投标，剩余期限应为：一个月30天
     * @param startDateStr  
     * @param endDateStr  
     * @return  
     */   
    public static String remainDateToString(String startDateStr, String endDateStr){   
    	java.util.Date startDate = null;   
        java.util.Date endDate= null;   
        try {   
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);   
            endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);   
        } catch (Exception e) {   
            e.printStackTrace();   
            return "--";   
        }   
        calS.setTime(startDate);   
        int startY = calS.get(Calendar.YEAR);   
        int startM = calS.get(Calendar.MONTH);   
        int startD = calS.get(Calendar.DATE);   
        int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);   
        
        calS.setTime(endDate);   
        int endY = calS.get(Calendar.YEAR);   
        int endM = calS.get(Calendar.MONTH); 
        int endD = calS.get(Calendar.DATE);   
           
        StringBuilder sBuilder = new StringBuilder();   
        if (endDate.compareTo(startDate)<0) {   
            return sBuilder.append("--").toString();   
        }
        if(endD>startDayOfMonth){//处理特殊情况
        	endD=startDayOfMonth;
        }
        int lday = endD-startD;  
        if (lday<0) {   
            endM = endM -1;   
            lday = startDayOfMonth+ lday;   
        }                     
        int mos = (endY - startY)*12 + (endM- startM);   
        int lyear = mos/12;   
        int lmonth = mos%12;   
        if (lyear >0) {   
            sBuilder.append(lyear+"年");   
        }   
        if (lmonth > 0) {   
            sBuilder.append(lmonth+"个月");   
        }  
        if (lday>0){
            sBuilder.append(lday+"天"); 
        }        
        return sBuilder.toString();    
    }
    /**  
     * 计算剩余时间  
     * 有bug 2015-12-31计息，2015-04-30到期 ，2015-03-01投标，剩余期限应为：一个月30天
     * @param startDateStr  
     * @param endDateStr  
     * @return  HashMap YEAR:年，MONTH:月，DAY:日
     */   
    @SuppressWarnings("rawtypes")
	public static HashMap remainDateToMap(String startDateStr, String endDateStr){   
    	java.util.Date startDate = null;   
    	java.util.Date endDate= null;   
    	HashMap<String, String> resultMap=new HashMap<String, String>();
    	try {   
    		startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateStr);   
    		endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateStr);   
    	} catch (Exception e) {   
    		e.printStackTrace();   
    		return resultMap;   
    	}   
    	calS.setTime(startDate);   
    	int startY = calS.get(Calendar.YEAR);   
    	int startM = calS.get(Calendar.MONTH);   
    	int startD = calS.get(Calendar.DATE);   
    	int startDayOfMonth = calS.getActualMaximum(Calendar.DAY_OF_MONTH);   
    	
    	calS.setTime(endDate);   
    	int endY = calS.get(Calendar.YEAR);   
    	int endM = calS.get(Calendar.MONTH); 
    	int endD = calS.get(Calendar.DATE);       	
    	if (endDate.compareTo(startDate)<0) {   
    		return resultMap;   
    	}
    	if(endD>startDayOfMonth){//处理特殊情况
    		endD=startDayOfMonth;
    	}
    	int lday = endD-startD;  
    	if (lday<0) {   
    		endM = endM -1;   
    		lday = startDayOfMonth+ lday;   
    	}                     
    	int mos = (endY - startY)*12 + (endM- startM);   
    	int lyear = mos/12;   
    	int lmonth = mos%12;   
    	if (lyear >0) {   
    		resultMap.put("YEAR", Integer.toString(lyear));
    	}   
    	if (lmonth > 0) {   
    		resultMap.put("MONTH",Integer.toString(lmonth) );
    	}  
    	if (lday>0){
    		resultMap.put("DAY",Integer.toString(lday));
    	}        
    	return resultMap;    
    }
    /**
     * remainDateToString
     * date2String:(计算剩余时间).
     *
     * @author lzp
     * @param s1
     * @param s2
     * @return
     * @throws ParseException
     * @since JDK 1.6
     */
	public static String remDateToString1(String s1, String s2)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date start = sdf.parse(s1);
		Date end = sdf.parse(s2);
		Calendar cs = Calendar.getInstance();
		cs.setTime(start);
		Calendar ce = Calendar.getInstance();
		ce.setTime(end);
		if (cs.after(ce)) {
			return "--";
		}
		int month = 0;//月份
		int year=ce.get(Calendar.YEAR) - cs.get(Calendar.YEAR);
		if (year > 0) {
			int totalMonth=(year*12 -cs.get(Calendar.MONTH) + ce.get(Calendar.MONTH) );
			month = totalMonth%12;
			year=totalMonth/12;
		} else {
			month = ce.get(Calendar.MONTH) - cs.get(Calendar.MONTH);

		}
		int day = 0;//天数
		if (ce.get(Calendar.DAY_OF_MONTH) >= cs.get(Calendar.DAY_OF_MONTH)) {
			day = ce.get(Calendar.DAY_OF_MONTH) - cs.get(Calendar.DAY_OF_MONTH);
		} else {
			Calendar cal = Calendar.getInstance();
			cal.setTime(end);
			cal.set(Calendar.DAY_OF_MONTH, 1);
			cal.add(Calendar.DAY_OF_MONTH, -1);
			// 上月最后一天日期
			int maxdayoflastmonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
			day = maxdayoflastmonth - cs.get(Calendar.DAY_OF_MONTH)
					+ ce.get(Calendar.DAY_OF_MONTH);
			month --;
		}
		return (year >0 ? year +"年":"")+(month >0 ? month +"月":"")+ (day > 0 ? day + "日":"");
	}
       
    /*  
     * 转换 dataAndTime 2013-12-31 23:59:59 到  
     * date 2013-12-31  
     */   
    public static String getDate(String dateAndTime){   
        if (dateAndTime != null && !"".equals(dateAndTime.trim())) {   
            Matcher   m   =  p.matcher(dateAndTime);    
            if (m.find()) {   
                  return dateAndTime.subSequence(m.start(), m.end()).toString();   
            }   
        }   
        return "data error";   
    }   

    //把yyyymmdd转成yyyy-MM-dd格式
    public static String formatDateStr2Str(String str) {
        String sfstr = "";
        try {
            sfstr = dateFormat.format(simpledateFormat.parse(str));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sfstr;
    }

    /**
     * 获得当前日期时间
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String currentDatetime() {
        return datetimeFormat.format(now());
    }

    /**
     * 获得当前日期时间
     * <p>
     * 日期时间格式yyyyMMddHHmmss
     * 
     * @return
     */
    public static String currentDatetime2() {
        return datetimeFormat2.format(now());
    }

    /**
     * 格式化日期时间
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     * 
     * @return
     */
    public static String formatDatetime(Date date) {
        return datetimeFormat.format(date);
    }

    /**
     * 格式化日期时间
     * 
     * @param date
     * @param pattern
     *            格式化模式，详见{@link SimpleDateFormat}构造器
     *            <code>SimpleDateFormat(String pattern)</code>
     * @return
     */
    public static String formatDatetime(Date date, String pattern) {
        SimpleDateFormat customFormat = (SimpleDateFormat) datetimeFormat.clone();
        customFormat.applyPattern(pattern);
        return customFormat.format(date);
    }

    /**
     * 获得当前日期
     * <p>
     * 日期格式yyyy-MM-dd
     * 
     * @return
     */
    public static String currentDate() {
        return dateFormat.format(now());
    }

    /**
     * 格式化日期
     * <p>
     * 日期格式yyyy-MM-dd
     * 
     * @return
     */
    public static String formatDate(Date date) {
        return dateFormat.format(date);
    }

    /**
     * 获得当前时间
     * <p>
     * 时间格式HH:mm:ss
     * 
     * @return
     */
    public static String currentTime() {
        return timeFormat.format(now());
    }

    /**
     * 格式化时间
     * <p>
     * 时间格式HH:mm:ss
     * 
     * @return
     */
    public static String formatTime(Date date) {
        return timeFormat.format(date);
    }

    /**
     * 获得当前时间的<code>java.util.Date</code>对象
     * 
     * @return
     */
    public static Date now() {
        return new Date();
    }

    public static Calendar calendar() {
        Calendar cal = GregorianCalendar.getInstance(Locale.CHINESE);
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        return cal;
    }

    /**
     * 获得当前时间的毫秒数
     * <p>
     * 详见{@link System#currentTimeMillis()}
     * 
     * @return
     */
    public static long millis() {
        return System.currentTimeMillis();
    }

    /**
     * 
     * 获得当前Chinese月份
     * 
     * @return
     */
    public static int month() {
        return calendar().get(Calendar.MONTH) + 1;
    }

    /**
     * 获得月份中的第几天
     * 
     * @return
     */
    public static int dayOfMonth() {
        return calendar().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 今天是星期的第几天
     * 
     * @return
     */
    public static int dayOfWeek() {
        return calendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 今天是年中的第几天
     * 
     * @return
     */
    public static int dayOfYear() {
        return calendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     *判断原日期是否在目标日期之前
     * 
     * @param src
     * @param dst
     * @return
     */
    public static boolean isBefore(Date src, Date dst) {
        return src.before(dst);
    }

    /**
     *判断原日期是否在目标日期之后
     * 
     * @param src
     * @param dst
     * @return
     */
    public static boolean isAfter(Date src, Date dst) {
        return src.after(dst);
    }

    /**
     *判断两日期是否相同
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isEqual(Date date1, Date date2) {
        return date1.compareTo(date2) == 0;
    }

    /**
     * 判断某个日期是否在某个日期范围
     * 
     * @param beginDate
     *            日期范围开始
     * @param endDate
     *            日期范围结束
     * @param src
     *            需要判断的日期
     * @return
     */
    public static boolean between(Date beginDate, Date endDate, Date src) {
        return beginDate.before(src) && endDate.after(src);
    }

    /**
     * 获得当前月的最后一天
     * <p>
     * HH:mm:ss为0，毫秒为999
     * 
     * @return
     */
    public static Date lastDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 0); // M月置零
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 1);// 月份+1
        cal.set(Calendar.MILLISECOND, -1);// 毫秒-1
        return cal.getTime();
    }

    /**
     * 获得当前月的第一天
     * <p>
     * HH:mm:ss SS为零
     * 
     * @return
     */
    public static Date firstDayOfMonth() {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_MONTH, 1); // M月置1
        cal.set(Calendar.HOUR_OF_DAY, 0);// H置零
        cal.set(Calendar.MINUTE, 0);// m置零
        cal.set(Calendar.SECOND, 0);// s置零
        cal.set(Calendar.MILLISECOND, 0);// S置零
        return cal.getTime();
    }

    private static Date weekDay(int week) {
        Calendar cal = calendar();
        cal.set(Calendar.DAY_OF_WEEK, week);
        return cal.getTime();
    }

    /**
     * 获得周五日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     * 
     * @return
     */
    public static Date friday() {
        return weekDay(Calendar.FRIDAY);
    }

    /**
     * 获得周六日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     * 
     * @return
     */
    public static Date saturday() {
        return weekDay(Calendar.SATURDAY);
    }

    /**
     * 获得周日日期
     * <p>
     * 注：日历工厂方法{@link #calendar()}设置类每个星期的第一天为Monday，US等每星期第一天为sunday
     * 
     * @return
     */
    public static Date sunday() {
        return weekDay(Calendar.SUNDAY);
    }

    /**
     * 将字符串日期时间转换成java.util.Date类型
     * <p>
     * 日期时间格式yyyy-MM-dd HH:mm:ss
     * 
     * @param datetime
     * @return
     */
    public static Date parseDatetime(String datetime) throws ParseException {
        return datetimeFormat.parse(datetime);
    }

    /**
     * 将字符串日期转换成java.util.Date类型
     *<p>
     * 日期时间格式yyyy-MM-dd
     * 
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date parseDate(String date) throws ParseException {
        return dateFormat.parse(date);
    }

    /**
     * 将字符串日期转换成java.util.Date类型
     *<p>
     * 时间格式 HH:mm:ss
     * 
     * @param time
     * @return
     * @throws ParseException
     */
    public static Date parseTime(String time) throws ParseException {
        return timeFormat.parse(time);
    }

    /**
     * 根据自定义pattern将字符串日期转换成java.util.Date类型
     * 
     * @param datetime
     * @param pattern
     * @return
     * @throws ParseException
     */
    public static Date parseDatetime(String datetime, String pattern) throws ParseException {
        SimpleDateFormat format = (SimpleDateFormat) datetimeFormat.clone();
        format.applyPattern(pattern);
        return format.parse(datetime);
    }

    /**
     * 当前（指定的）日期下一个月（或者N各月）
     * @Title: addMonth
     * @Description: 当前（指定的）日期下一个月（或者N各月）
     * @param s  指定一个时间
     * @param n  需要增加的月份
     * @return 
     * @throws ParseException
     */
    public static String addMonth(String s, int n) throws ParseException {
        Calendar cd = Calendar.getInstance();
        cd.setTime(dateFormat.parse(s));
        //	 cd.add(Calendar.DATE, n); //增加一天   
        cd.add(Calendar.MONTH, n);//增加一个月   
        return dateFormat.format(cd.getTime());
    }
    /**
     * 
     * addDate:当前（指定的）日期下一个日（或者N日）
     *
     * @author lzp
     * @param s 指定一个时间
     * @param n 需要增加的天数
     * @return
     * @throws ParseException
     * @since JDK 1.6
     */
    public static String addDate(String s, int n) throws ParseException {
        Calendar cd = Calendar.getInstance();
        cd.setTime(dateFormat.parse(s));
        cd.add(Calendar.DATE, n); //增加n天   
        return dateFormat.format(cd.getTime());
    }

    /**
     * 指定时间的中文星期
     * @Title: getWeekDay
     * @Description: 获取指定时间的中文星期
     * @param date 指定的时间
     * @return
     */
    public static String getWeekDay(Date date) {
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        return dateFm.format(date);
    }

    /**
     * 格式化日期 ：将长时间的日期转换为短时间的日期
     * 日期格式前：yyyy-MM-dd HH:mm
     * 日期格式后：yyyy-MM-dd
     * 
     * @return
     * @throws ParseException 
     */
    public static String formatDate(String strdate) throws ParseException {
        Date date = DateUtils.parseDate(strdate);
        String newstrDate = DateUtils.formatDate(date);
        return newstrDate;
    }

    /**
     * 指定时间转长整形
     * 
     * @param dateStr
     * @return
     */
    public static long getLongTime(String dateStr) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long l = 0;
        try {
            Date date = df.parse(dateStr);
            return date.getTime();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return l;
    }
    
 
    public static void main(String[] args) {
    	
    	String DD=DateUtils.remainDateToString("2016-03-01","2016-04-30");
    	System.out.println(DD);
    	Date date=new Date(2015, 11, 13, 8, 45, 19);
    	System.out.println(date.getMinutes());
	}
}
