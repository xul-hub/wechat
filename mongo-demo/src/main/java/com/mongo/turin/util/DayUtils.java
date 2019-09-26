package com.mongo.turin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DayUtils {
	
	private static final int YESTERDY = -1;
    private static final int TODAY = 0;
    private static final int TOMORROWDAT = 1;
    private static final int OTHER_DAY = 10000;
 
    private static ThreadLocal<SimpleDateFormat> DateLocal = new ThreadLocal<SimpleDateFormat>();
 
    /**
     * ��ȡ���ڵĸ�ʽ
     */
    public static SimpleDateFormat getDateFormat() {
        if (null == DateLocal.get()) {
        	DateLocal.set(new SimpleDateFormat("yyyyMMdd", Locale.CHINA));
        }
        return DateLocal.get();
    }
 
    /**
     * ������ʾ����
     */
    public static String getTitleDay(String day){
        try {
            /*
            boolean isToday;
            boolean isYesterday;
            boolean isTomorrowday;
            isToday = IsToday(day);
            isYesterday = IsYesterday(day);
            isTomorrowday = IsTomorrowday(day);
            if(isToday){
                return "����";
            }else if(isYesterday){
                return "����";
            }else if(isTomorrowday){
                return "����";
            }else{
                return day;
            }*/
            switch (JudgmentDay(day)) {
                case YESTERDY : {
                    return "����";
                }
                case TODAY : {
                    return "����";
                }
                case TOMORROWDAT : {
                    return "����";
                }
                default:
                    return day;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /**
           * �ж�����(Ч�ʱȽϸ�)
     */
    public static int JudgmentDay(String day) throws ParseException {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
 
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
 
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
 
            switch (diffDay) {
                case YESTERDY : {
                    return YESTERDY;
                }
                case TODAY : {
                    return TODAY;
                }
                case TOMORROWDAT : {
                    return TOMORROWDAT;
                }
            }
        }
        return OTHER_DAY;
    }
 
    /**
     * �ж��Ƿ�Ϊ����(Ч�ʱȽϸ�)
     * @param day ����� ʱ��  "2016-06-28 10:10:30" "2016-06-28" ������
     * @return true���� false����
     * @throws ParseException
     */
    public static boolean IsYesterday(String day) {
 
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
 
        Calendar cal = Calendar.getInstance();
        Date date;
		try {
			date = getDateFormat().parse(day);
			cal.setTime(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
       
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
            if (diffDay == YESTERDY) {
                return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
		System.out.println(IsYesterday("20190108"));
	}
    
    /**
     * �ж��Ƿ�Ϊ����(Ч�ʱȽϸ�)
     * @param day ����� ʱ��  "2016-06-28 10:10:30" "2016-06-28" ������
     * @return true���� false����
     * @throws ParseException
     */
    public static boolean IsToday(String day) throws ParseException {
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
 
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
 
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
 
            if (diffDay == TODAY) {
                return true;
            }
        }
        return false;
    }
 
    /**
     * �ж��Ƿ�Ϊ����(Ч�ʱȽϸ�)
     * @param day ����� ʱ��  "2016-06-28 10:10:30" "2016-06-28" ������
     * @return true���� false����
     * @throws ParseException
     */
    public static boolean IsTomorrowday(String day) throws ParseException {
 
        Calendar pre = Calendar.getInstance();
        Date predate = new Date(System.currentTimeMillis());
        pre.setTime(predate);
 
        Calendar cal = Calendar.getInstance();
        Date date = getDateFormat().parse(day);
        cal.setTime(date);
 
        if (cal.get(Calendar.YEAR) == (pre.get(Calendar.YEAR))) {
            int diffDay = cal.get(Calendar.DAY_OF_YEAR) - pre.get(Calendar.DAY_OF_YEAR);
 
            if (diffDay == TOMORROWDAT) {
                return true;
            }
        }
        return false;
    }

}
