package ssh;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.junit.Test;

import com.hoyatod.util.DateUtil;

public class RuleTest {
	
	
	@Test
	public void ruleTest() {
		System.out.println("推广期："+doubleIntegralRule(new Date(),0));
		System.out.println("周年庆："+doubleIntegralRule(new Date(),1));
	}
	
	private boolean doubleIntegralRule(Date date,int type) {
		Map<String, String> doubleIntegral = readProperties("integralRule.properties");
		int now = Integer.parseInt(DateUtil.dateToStr(date, DateUtil.DATESYTLE3));   
		if(type == 0) {
			int _start = Integer.parseInt(doubleIntegral.get("startSpread"));
			int _end = Integer.parseInt(doubleIntegral.get("endSpread"));
			if(now >_end) {
				return false;
			}else if (now < _start) {
				return false;
			}else {
				return true;
			}
		}else {
			int _start = Integer.parseInt(doubleIntegral.get("yhAnniversaryStartTime"));
			int _end = Integer.parseInt(doubleIntegral.get("yhAnniversaryeEndTime"));
			if(now >_end) {
				return false;
			}else if (now < _start) {
				return false;
			}else {
				return true;
			}
		}
		
	}
	
	private Map<String,String> readProperties(String propertiesFileName){
		try {
			InputStreamReader is = new InputStreamReader(RuleTest.class.getClassLoader().getResourceAsStream(propertiesFileName),"UTF-8");
			Properties prop = new Properties();
			Map<String,String> result = new HashMap<String,String>();
			prop.load(is);
			Set<Object> keyset = prop.keySet();
           Iterator<Object> itr = keyset.iterator();
           while(itr.hasNext()){
               String key = (String) itr.next();
               result.put(key, prop.getProperty(key));
           }
			return result;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
