package com.mongo.turin.util;

import java.util.HashMap;
import java.util.Map;

import com.mongo.turin.entity.InputText;
import com.mongo.turin.entity.Perception;
import com.mongo.turin.entity.Root;
import com.mongo.turin.entity.UserInfo;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

public class TurinApiUtil {
	
	private static final Map<String, Object> maps = new HashMap<String, Object>();
	public static final String URL = "http://openapi.tuling123.com/openapi/api/v2";
	public static final String apikey = "97f7a1d803c54059bc7292c545f4b4ec";
	public static final String secretkey = "8ebcfe143e2e18ee";
	
	

	
	/**
	* @Description: 该函数的功能描述
	*
	* @param:描述1描述
	* @return：返回结果描述
	* @throws：异常描述
	*
	* @version: v1.0.0
	* @author: Administrator
	* @date: 2019年1月3日 上午10:35:44 
	*/
	public static Root doPsotTurinApi(String text) { 
		
		Perception p = new Perception();
		p.setInputText(new InputText(text));
		p.setInputImage(null);
		p.setSelfInfo(null);
		
		maps.put("reqType", 0);
		maps.put("perception", p);
		
		UserInfo u = new UserInfo();
		u.setApiKey(apikey);
		u.setUserId("admin");
		
		maps.put("userInfo", u);
		
		JSONObject fromObject = JSONObject.fromObject(OkhttpUtil.doPostHttpRequest(URL, JSONObject.fromObject(maps).toString()));
		
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		JSONArray json = JSONArray.fromObject(fromObject.get("results"), jsonConfig);
		
		JSONObject jsonObject=JSONObject.fromObject(json.get(0).toString());
		Root r=(Root)JSONObject.toBean(jsonObject, Root.class);
		
		return r;
	}
	public static void main(String[] args) {
		Root root = doPsotTurinApi("我長得帥嗎？");
		System.out.println(root);
		
	}
}
