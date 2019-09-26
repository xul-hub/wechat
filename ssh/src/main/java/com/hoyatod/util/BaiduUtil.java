package com.hoyatod.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class BaiduUtil {
	
	private static final double EARTH_RADIUS = 6378.137;// 地球半径,单位千米
	 
	private static double rad(double d) {
                //角度转换成弧度
		return d * Math.PI / 180.0;
	}
 
	/**
	 * 计算两个地点的距离
	 * 
	 * @param lat1 第一个纬度
	 * @param lng1 第一个经度
	 * @param lat2 第二个纬度
	 * @param lng2 第二个经度
	 * @return 两个经纬度的距离
	 */
	public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);//纬度
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;//两点纬度之差
		double b = rad(lng1) - rad(lng2);//经度之差
 
                //计算两点之间距离的公式
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
                //弧长乘地球半径（半径）
		s = s * EARTH_RADIUS;
                //精确距离的数值（单位千米）
		s = Math.round(s * 10000) / 10000;
                return s;
	}
	/**
	 * 获取经纬度所在城市
	 * 
	 * @param lat 纬度
	 * @param lng 经度
	 * @return 所属城市
	 */
	public static String getCity(double lat, double lng) throws Exception {
		String json = getLocationInfo(lat, lng, false);
		System.out.println(json); // 打印结果json
		if (json != null && json.startsWith("renderReverse&&renderReverse")) {
			
			/*
			  * 返回数据
			  * 例：renderReverse&&renderReverse({"status":0,"result":{"location":{"lng":121.
			 * 47999999999992,"lat":31.219999865070787},"formatted_address":"上海市黄浦区合肥路550号",
			 * "business":"新天地,复兴中路,瑞金医院","addressComponent":{"country":"中国","country_code":
			 * 0,"province":"上海市","city":"上海市","district":"黄浦区","adcode":"310101","street":
			 * "合肥路","street_number":"550号","direction":"附近","distance":"1"},"pois":[],
			 * "poiRegions":[],"sematic_description":"海文商务楼西68米","cityCode":289}})
			 */
			
			json = json.replace("renderReverse&&renderReverse(", "");   // 截取中间的json数据
			json = json.substring(0, json.length() - 1);
			
			JSONObject dataJson = new JSONObject(json);					// 解析得到的json格式数据
			JSONObject result = dataJson.getJSONObject("result");
			JSONObject addressComponent = result.getJSONObject("addressComponent");
			String city = addressComponent.getString("city");
			return city;
		} else {
			throw new Exception("异常出现：调用 getLocationInfo() 返回结果异常");
		}
	}
 
	public static void main(String[] args) {
		System.out.println("ch:"+getDistance(11.5, 115, 11.6, 115));
		System.out.println(getLocationInfo(11.5, 115, false));
	}
	/**
	 * 获取地址Json字符串
	 * 
	 * @param lat    纬度
	 * @param lng    经度
	 * @param around 是否显示周边的poi（为true默认显示周边1000米内的poi）
	 * @return
	 */
	private static String getLocationInfo(double lat, double lng, boolean around) {
		String data = null;
		//PropertiesUtils proUtils = new PropertiesUtils();
		/** 我选择在 config.properties 配置 baidu.ak，这里你可以用自己的百度AK */
		/*String ak = proUtils.getValue("baidu.ak");
		if (StringUtils.isBlank(ak)) {
			System.out.println("baidu ak is null");
			return null;
		}*/
		String ak = "BBuXHPN5GtUVjwOHVUpOTMzbEDDkTGUo";
		String url = "http://api.map.baidu.com/geocoder/v2/?callback=renderReverse&location=" + lat + "," + lng + "&output=json&pois=" + around + "&ak=" + ak;
		URL myURL = null;
		URLConnection httpsConn = null;
		try {
			myURL = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		InputStreamReader insr = null;
		BufferedReader br = null;
		try {
			httpsConn = (URLConnection) myURL.openConnection();// 不使用代理
			if (httpsConn != null) {
				insr = new InputStreamReader(httpsConn.getInputStream(), "UTF-8");
				br = new BufferedReader(insr);
				data = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (insr != null) {
					insr.close();
				}
				if (br != null) {
					br.close();
				}
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		return data;
	}
}
