package ssh;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import com.hoyatod.util.OkhttpUtil;

public class TestChang {
	
	@Test
	public void test4() throws IOException {
		for (int i = 0; i < 10000; i++) {
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					System.out.println("时间："+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
					String doGetHttpRequest = null;
					try {
						doGetHttpRequest = OkhttpUtil.doGetHttpRequest("url");
						System.out.println(doGetHttpRequest);
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			});
			System.out.println(t.getName());
			t.run();
		}
		
	}
	@Test
	public void test1() {
		String data = "2019-09-13 09:14:00";
		final String [] datas = new String [] {"2019-09-13","2019-09-14","2019-09-15","2019-10-01","2019-10-02","2019-10-03","2019-10-04","2019-10-05","2019-10-06","2019-10-07"};
		for (String times : datas) {
			int concat = data.indexOf(times);
			System.out.println(concat);
			break;
		}
	}
	
}
