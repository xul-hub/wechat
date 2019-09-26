package com.mybatis.utils.https;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.mybatis.bdtj.data.OkhttpUtil;

public class TestCheck {
	
	@Test
	public void test1() throws IOException{
		Map<String,String> createMap = new HashMap<String,String>();
		createMap.put("username","fkg");
		createMap.put("password","***");
		System.out.println(HttpsUrl.l.replace("CODE", "15897899920"));
		String doPost = ClientUtil.doPost(HttpsUrl.l.replace("QQ", "15897899920"),createMap);
		String doGetHttpRequest = OkhttpUtil.doGetHttpRequest(HttpsUrl.l.replace("CODE", "15897899920"));
		System.out.println(doGetHttpRequest);
	}
}
