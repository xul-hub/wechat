package com.hoyatod.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
	
	public static boolean isNotBlank(String str) {
		if(str != null && str.length() > 0 && str.trim().length() > 0)
			return true;
		return false;
	}
	public static boolean checkPhoneNo(String phone) {
		Pattern p = Pattern.compile(
				"^[1][3,5,7,8]\\d{9}$");
		Matcher matcher = p.matcher(phone);
		return matcher.matches();
	}
	
	public static void main(String[] args) {
		System.out.println(checkPhoneNo("15597899965"));
	}
	
	@SuppressWarnings("static-access")
	public static Date getYesterdayDate() {
		Date date = new Date();
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.add(calendar.DATE,-1);
		Date newDate = calendar.getTime();
		return newDate;
	}
	
	public static String thisMonthEnd(Calendar localTime,int x,int y) {
        String strY = null;
        String strZ = null;
        boolean leap = false;
        if (y == 1 || y == 3 || y == 5 || y == 7 || y == 8 || y == 10 || y == 12) {
            strZ = "31";
        }
        if (y == 4 || y == 6 || y == 9 || y == 11) {
            strZ = "30";
        }
        if (y == 2) {
            leap = leapYear(x);
            if (leap) {
                strZ = "29";
            }
            else {
                strZ = "28";
            }
        }
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-" + strZ;
    }
	
	public static String thisMonth(Calendar localTime,int x,int y) {
        String strY = null;
        strY = y >= 10 ? String.valueOf(y) : ("0" + y);
        return x + "-" + strY + "-01";
    }
	
	 /**
     * 功能：判断输入年份是否为闰年<br>
     * 
     * @param year
     * @return 是：true  否：false
     * @author pure
     */
    public static boolean leapYear(int year) {
        boolean leap;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) leap = true;
                else leap = false;
            }
            else leap = true;
        }
        else leap = false;
        return leap;
    }
}
