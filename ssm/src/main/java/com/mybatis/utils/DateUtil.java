package com.mybatis.utils;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public final static String DATESYTLE1 = "yyyy-MM-dd";
    public final static String DATESYTLE2 = "yyyy-MM-dd HH:mm:ss";
    public final static String DATESYTLE3 = "yyyyMMdd";
    public final static String DATESYTLE4 = "yyyyMMddHHmmss";
    public final static String DATESTYLE5 = "MM-dd HH:mm";
    
    /**
     * 获取当前年份
     * @return
     */
    public static int getNowYear() {
    	Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year;
    }

    public static boolean dateEquals(Date date1, Date date2) {
        if (date1 == null || date2 == null) return false;
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }
    
    public static Date stringtoDate(String dateStr, String format) {
        Date d = null;
        if (dateStr != null && dateStr.trim().length() > 0) {
            SimpleDateFormat formater = new SimpleDateFormat(format);
            try {
                formater.setLenient(false);
                d = formater.parse(dateStr);
            } catch (Exception e) {
                d = null;
            }
        }
        return d;
    }

    public static String dateToStr(Date date, String formatStyle) {
        if (date == null) return "1999-09-09 09:09:09";
        SimpleDateFormat formatStr = new SimpleDateFormat(formatStyle);
        String str = formatStr.format(date);
        return str;
    }
    

    public static Long dateToInteger(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Long t1 = cal.getTimeInMillis();
        return t1;
    }

    public static Date longToDate(Long t2) {
        Date date = new Date(t2);
        return date;
    }
    
    /**
     * 把长整型的时间转换成字符串形式
     * @param date
     * @param formatStyle
     * @return
     */
    public static String longToString(long date,String formatStyle) {
        SimpleDateFormat formatStr = new SimpleDateFormat(formatStyle);
        String str = formatStr.format(date);
        return str;
    }
    /**
     * 把字符串形式的时间转换成长整型
     * @param date
     * @return
     */
    public static long stringToLong(String date) {
    	try {
    		Timestamp st = Timestamp.valueOf(date);
    		return st.getTime();
    	} catch(Exception e) {
    		return 0;
    	}
    }
    /**
     * 把字符串形式的时间转换成Timestamp对象
     * @param date
     * @return
     */
    public static Timestamp stringToTimestamp(String date) {
    	try {
    		Timestamp st = Timestamp.valueOf(date);
    		return st;
    	} catch(Exception e) {
    		return null;
    	}
    }
    /**
     * 两日期相减(beginDate-endDate)，返回相减后的天数，只接受yyyy-MM-dd格式的时间
     * @param beginDate
     * @param endDate
     * @return
     */
    public static long getDaySub(String beginDate,String endDate) {
    	 long day = 0;
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");    
         Date begin = null;
         Date end = null;
         try {
        	 begin = format.parse(beginDate);
        	 end= format.parse(endDate);    
             day = (begin.getTime()-end.getTime())/(24*60*60*1000);    
         } catch (ParseException e) {
             e.printStackTrace();
         }   
         return day;
    }

    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, +1);
        date = calendar.getTime();
        return date;
    }
    
    public static Date getNextHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, +1);
        date = calendar.getTime();
        return date;
    }
    
    /**
	 * 比较两个时间之差是否在一小时以内
	 * @param preDate 较前的时间
	 * @param date 较后的时间
	 *   date应比preDate大
	 * **/
	public static boolean isInAnHour(Date preDate,Date date){
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(preDate);
		int preHour = calendar.get(Calendar.HOUR_OF_DAY);
		calendar.set(Calendar.HOUR_OF_DAY, preHour+1);
		return calendar.getTime().after(date);
	}

	/**
	 * 获取N天以前的时间
	 * @param n可以为正负数，负数时获取的结果为N天以后的时间，注意灵活使用
	 * @return N天以前的时间
	 * **/
	public static Date nDaysAgo(Date date ,int n){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - n);
		return calendar.getTime();
	}

    public static void main(String[] args) {
        Date date1 = DateUtil.stringtoDate("2012-01-10", DATESYTLE1);
        Date date2 = DateUtil.stringtoDate("2012-02-28", DATESYTLE1);

        System.out.println(date1 + "\n" + date2);
        // System.out.println(DateUtil.dateEquals(date1, date2));
        System.out.println(DateUtil.dateToInteger(new Date()));

    }
    /**
     * 比较两个时间(请自己校验参数，同时只支持DateUtil下的DATESYTLE2格式)
     * @param firstDate 第一个时间
     * @param secondDate 第二个时间
     * @return 如果时间相等返回0，firstDate大于secondDate，返回大于0，反之返回小于0
     */
    public static int compare(String firstDate,String secondDate){
    	DateFormat df=new java.text.SimpleDateFormat(DATESYTLE2);
    	Calendar c1=Calendar.getInstance();
    	Calendar c2=Calendar.getInstance();
    	try {
			c1.setTime(df.parse(firstDate));
			c2.setTime(df.parse(secondDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return c1.compareTo(c2);
    }
    public static int compare_1(String firstDate,String secondDate,String format){
    	DateFormat df=new java.text.SimpleDateFormat(format);
    	Calendar c1=Calendar.getInstance();
    	Calendar c2=Calendar.getInstance();
    	try {
			c1.setTime(df.parse(firstDate));
			c2.setTime(df.parse(secondDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}
    	return c1.compareTo(c2);
    }
    /**
     * 计算两个日期相隔多少天
     * @param firstDate
     * @param secondDate
     * @return
     */
   public static int DateDistance(String firstDate,String secondDate){
	   SimpleDateFormat sdf=new SimpleDateFormat(DATESYTLE1);
	   try {
		   Date d1=sdf.parse(firstDate);
		   Date d2=sdf.parse(firstDate);
		  return (int)(d2.getTime()-d1.getTime()+1000000)/(3600*24*1000);
	   } catch (ParseException e) {
			e.printStackTrace();
	   }
	   return 0;
   }
   /**  
    * 计算两个日期之间相差的天数  
    * @param smdate 较小的时间 
    * @param bdate  较大的时间 
    * @return 相差天数 
    * @throws ParseException  
    */    
   public static int daysBetween(Date smdate,Date bdate) throws ParseException    
   {    
       SimpleDateFormat sdf=new SimpleDateFormat(DATESYTLE1);  
       smdate=sdf.parse(sdf.format(smdate));  
       bdate=sdf.parse(sdf.format(bdate));  
       Calendar cal = Calendar.getInstance();    
       cal.setTime(smdate);    
       long time1 = cal.getTimeInMillis();                 
       cal.setTime(bdate);    
       long time2 = cal.getTimeInMillis();         
       long between_days=(time2-time1)/(1000*3600*24);  
           
      return Integer.parseInt(String.valueOf(between_days));           
   }    
     
   /** 
    *字符串的日期格式的计算 
    */  
   public static int daysBetween(String smdate,String bdate) throws ParseException{  
       SimpleDateFormat sdf=new SimpleDateFormat(DATESYTLE1);  
       Calendar cal = Calendar.getInstance();    
       cal.setTime(sdf.parse(smdate));    
       long time1 = cal.getTimeInMillis();                 
       cal.setTime(sdf.parse(bdate));    
       long time2 = cal.getTimeInMillis();         
       long between_days=(time2-time1)/(1000*3600*24);  
           
      return Integer.parseInt(String.valueOf(between_days));     
   }  
}
