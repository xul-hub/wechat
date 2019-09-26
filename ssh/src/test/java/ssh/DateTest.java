package ssh;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class DateTest {
	
	@Test
	public void test1() throws ParseException {
		String str = "2019-08-01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        Date date=null;
        try {
            date = sdf.parse(str);
            calendar.setTime(date);
            int day=calendar.get(Calendar.DATE);
            calendar.set(Calendar.DATE,day+2);//-1为前一天    此处修改为+1则是获取后一天
            
            String lastDay = sdf.format(calendar.getTime());
            String today = sdf.format(new Date());
            System.out.println(lastDay.equals(today));
        } catch (ParseException e) {
            e.printStackTrace();
        }
	}
	@Test
	public void belongCalendar() throws ParseException {
//		Date nowTime = new Date();
		
		Calendar calendar = Calendar.getInstance();
		String str = "2019-08-03";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date beginTime = sdf.parse(str);
		calendar.setTime(beginTime);
		int day=calendar.get(Calendar.DATE);
		calendar.set(Calendar.DATE,day+2);//-1为前一天    此处修改为+1则是获取后一天
		Date endTime = calendar.getTime();
		System.out.println("endTime:"+endTime);
		String format = sdf.format(new Date());
		Date nowTime = sdf.parse(format);
		System.out.println("nowTime:"+nowTime);
		
		Calendar date = Calendar.getInstance();
		date.setTime(nowTime);
		Calendar end = Calendar.getInstance();
		end.setTime(endTime);
		
		if(date.before(end)) {
			System.out.println("1:" + true);
		}else if (nowTime.compareTo(endTime) == -1 || nowTime.compareTo(endTime) == 0) {
			System.out.println("2:" + true);
		}else {
			System.out.println("3:" + false);
		}
	} 
	
}
