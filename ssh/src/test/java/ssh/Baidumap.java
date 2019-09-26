package ssh;

import java.io.IOException;
import java.util.Date;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.hoyatod.util.HttpClient;
import com.hoyatod.util.HttpInvoker;

public class Baidumap {
//	http://api.map.baidu.com/highacciploc/v1?qcip=139.214.254.47&qterm=pc&ak=naW5VG6u5TELUZ2stDslQPsYirKoIOFz&coord=bd09ll&extensions=3
	public static final String _api = "https://apis.map.qq.com/ws/location/v1/ip?ip=119.98.201.111&key=您的密钥";
	public static final String api = "https://api.map.baidu.com/location/ip?ip=119.98.201.111&ak=您的密钥&coor=bd09ll";
	public static final String api_d = "http://api.map.baidu.com/routematrix/v2/driving?output=json&origins=40.45,116.34|40.54,116.35&destinations=40.34,116.45|40.35,116.46&ak=您的AK";
	public static final String api_http = "https://map.baidu.com/?qt=ipLocation&t=111";
	
	public static final String translates = "https://apis.map.qq.com/ws/coord/v1/translate?locations=30.49984,114.34253&type=3&key=您的密钥";
	@Test
	public void map() throws IOException {
		String httpGet = HttpInvoker.httpGet(_api, null);
		Object parse = JSON.parse(httpGet);
		System.out.println(parse);
		//"location":{"lng":114.34253,"lat":30.49984}},"message":"query ok","status":0}

	}
	
	@Test
	public void map1() throws IOException {
		String httpGet = HttpInvoker.httpGet(api_http, null);
		System.out.println(httpGet);
	}
	
	@Test
	public void map2() throws IOException {
		String httpGet = HttpInvoker.httpGet(translates, null);
		Object parse = JSON.parse(httpGet);
		System.out.println(parse);
	}
}
