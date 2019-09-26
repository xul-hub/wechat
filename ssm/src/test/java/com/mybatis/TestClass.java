package com.mybatis;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.junit.Test;

import com.mybatis.utils.DateUtil;
import com.mybatis.utils.LinkedListQueue;

@SuppressWarnings({"deprecation","static-access"})
public class TestClass {
	
	
	@Test
	public void test1(){
		long currentTimeMillis = System.currentTimeMillis();
		try {
			new Thread( () -> System.out.println("In Java10, Lambda expression rocks !!") ).sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long endTimeMillis  = System.currentTimeMillis();
		Date nowTime = new Date(endTimeMillis -currentTimeMillis);
		System.out.println("时间差：" + nowTime.getSeconds());
		System.out.println();
	}
	
	@Test
	public void test2() {
		ArrayList<String> list  = new ArrayList<>(); 
		list.add("hello");
		list.add("world!");
		list.forEach(value -> System.out.println(value));
	}
	
	@Test
	public void test3() {
		Calendar now=Calendar.getInstance();
		int now_time =  now.get(Calendar.HOUR_OF_DAY);
		System.out.println(now_time);
		if(now_time >= 0 && now_time < 10) {
			System.out.println(true);
		}else {
			System.out.println(false);
		} 
	}
	
	@Test
	public void test4() {
		 System.out.println(fillStringWithLength("12000000",'0',true,10));
	}
	
	
	
	@Test
	public void test5() {
		int cj_int = cj_20160124();
		System.out.println(cj_int);
		int randomcash = 0;
		int sendstatus_ = 1;
		String getnowTime_str = getnowTime();
		if(cj_int == 1) {
			randomcash = randomcash();
			sendstatus_ =0;
			getnowTime_str = getnowTime_str+"00";
		}
		else{
			System.out.println("2121");
//			if(currentMember_count_01 == 0)  
//				getnowTime_str = getnowTime_str+"01";
//			else{
//				if(currentMember_count_02 == 0)
//				    getnowTime_str = getnowTime_str+"02";
//				else
//					getnowTime_str = getnowTime_str+"03";	
//			}
		}
	}
	
	@Test
	public void test6() {
		System.out.println(getradom_3());
	}
	
	@Test
	public void test7() {
		for(int i=0;i<1000;i++){
           sendMsg( Integer.toString( i ) );
		}
	}
	
	 public static void sendMsg(String msg) {
		 LinkedListQueue tpm = LinkedListQueue.newInstance();
	     tpm.addLogMsg( msg + "记录一条日志 " );
	 }
	     
	 @Test
	public void test8() {
		List<Integer> arrs = new ArrayList<>();
		for(int i=1;i<=30;i++){
          arrs.add(i);
		}
//		System.out.println(arrs.get(1));
		int j = 1;
		int check = 0;
		while (j <= 15) {   
			for (int i = 1; i <= 30; i++) {
				if(arrs.get(i-1) == 0) {
					continue;
				}else {
					check++;
					if(check == 9) {
						arrs.set(i-1, 0);
						System.out.println(i + "号下船了");
						check = 0;
						j++;
					}else {
						continue;
					}
				}
			}
		}
	}
	
	@Test
	public void test9() {
		 Date date=new Date();//取时间
		 Calendar calendar = new GregorianCalendar();
		 calendar.setTime(date);
		 calendar.add(calendar.DATE,-1);//把日期往后增加一天.整数往后推,负数往前移动
		 date=calendar.getTime(); //这个时间就是日期往后推一天的结果 
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		 String dateString = formatter.format(date);
		 
		 System.out.println(dateString);
	}
	
	
	public String  getradom_3() {
		 String str="赵钱孙李周吴郑王";  
	        Random random = new Random();  
	        StringBuffer sb = new StringBuffer();  
	          
	        for(int i = 0 ; i < 1; ++i){  
	            int number = random.nextInt(8);//[0,62)  
	            
	            sb.append(str.charAt(number));
	        }  
	        return sb.toString();
	}
	
	public static String getnowTime(){
		Date date = new Date();
		String sx_ = DateUtil.dateToStr(date,DateUtil.DATESYTLE3);
		return sx_;
	}
	public static int cj_20160124() {
		int money[] = {1,0,0,0,0,0,0,0,0,0};
		int j = new Random().nextInt(10); 
		return money[j];
	}
	
	public static int randomcash() {
		return 100 + new Random().nextInt(30);
	}
	public static String fillStringWithLength(String str,char fillChar,boolean fillBefore,int length) {
        if(str.length() >= length){
        	return str;
        }
        int fillLen = length - str.length();
        StringBuffer sb = new StringBuffer();
        for(int i = 0 ; i < fillLen ; i++){
        	sb.append(fillChar);
        }
        if(fillBefore){
        	sb.append(str);
        	return sb.toString();
        }else{
        	return str + sb.toString();
        }
    }
}

