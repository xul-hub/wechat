package ssh;

import java.io.IOException;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hoyatod.config.Configure;
import com.hoyatod.util.HttpInvoker;

public class TxMapTest {//23.140093,113.326308
	//https://apis.map.qq.com/ws/coord/v1/translate?locations=39.071510,117.190091;....&type=5&key=您的密钥
	@Test
	public void test1() throws IOException {
		final String translate = "https://apis.map.qq.com/ws/coord/v1/translate?locations=39.071510,117.190091&type=1&key=您的密钥";
		String httpGet = HttpInvoker.httpGet(translate, null);
//		System.out.println("httpGet:"+httpGet);
		JSONObject parseObject = JSON.parseObject(httpGet);
		JSONArray jsonArray = parseObject.getJSONArray("locations");
		Object o = jsonArray.get(0);
		if(o != null) {
			JSONObject parse = JSON.parseObject(o.toString());
			System.out.println("经度："+parse.get("lng"));
			System.out.println("纬度："+parse.get("lat"));
		}
	}
	
	@Test
	public void test2() throws IOException {//980.17km  23.140093,113.326308   武汉30.532813,114.360129
		System.out.println("--------------一对多-------------");
		String httpGet = HttpInvoker.httpGet(com.hoyatod.constants.TXSDK.CalDistance.replace("FROMPOSITION", "30.530273,114.361509").replace("TOPOSITION", "30.575645,114.322183").replace("MAP_KEY", Configure.tengxun_map_key), null);
		JSONObject parseObject = JSON.parseObject(httpGet);
		JSONObject jsonObject = parseObject.getJSONObject("result");
		
		System.out.println("result1:"+jsonObject);
		if(jsonObject != null) {
			JSONArray elements = jsonObject.getJSONArray("elements");
			if(elements != null && elements.size() > 0) {
				for (int i = 0; i < elements.size(); i++) {
					String distance = JSON.parseObject(elements.get(i).toString()).getString("distance");
					if(distance != null) {
						System.out.println("结果1："+distance);
					}
				}
			}
		}
		System.out.println("-------------多对多------------------");
		String httpGet1 = HttpInvoker.httpGet(com.hoyatod.constants.TXSDK.CalDistance1.replace("FROMPOSITION", "30.530273,114.361509").replace("TOPOSITION", "30.575645,114.322183").replace("MAP_KEY", Configure.tengxun_map_key), null);
		JSONObject parseObject1 = JSON.parseObject(httpGet1);
		JSONObject jsonObject1 = parseObject1.getJSONObject("result");
		System.out.println("result2:"+jsonObject1);
		if(jsonObject1 != null) {
			JSONArray rows = jsonObject1.getJSONArray("rows");
			if(rows != null && rows.size() > 0) {
				JSONObject parseObject2 = JSON.parseObject(rows.get(0).toString());
				if(parseObject2 != null && parseObject2.size() > 0) {
					JSONArray elements = parseObject2.getJSONArray("elements");
					if(elements != null && elements.size() > 0) {
						for (int i = 0; i < elements.size(); i++) {
							String distance = JSON.parseObject(elements.get(i).toString()).getString("distance");
							if(distance != null) {
								System.out.println("结果2："+distance);
							}
						}
					}
				}
			}
		}
		/*
		if(jsonObject1 != null) {
			JSONArray jsonArray = jsonObject1.getJSONArray("rows");
			if(jsonArray != null) {
				JSONArray elements = jsonArray.getJSONArray("elements");
				if(elements != null && elements.size() > 0) {
					for (int i = 0; i < elements.size(); i++) {
						String distance = JSON.parseObject(elements.get(i).toString()).getString("distance");
						if(distance != null) {
							System.out.println("结果2："+distance);
						}
					}
				}
			}
		}*/
	}
}
