package com.mybatis.utils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class WechatTools {
	
	private static Logger log = Logger.getLogger(WechatTools.class);
	
	public static String getAccess_token() {
        //微信令牌请求网址(由微信提供)
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxab573c02af878c77&secret=719fa772aedff2b6a12b29fa40109d62";
        String accessToken = null;
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");               
            JSONObject json = new JSONObject(message);
            accessToken = json.getString("access_token");
            is.close();
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace(); 
            log.error("获取失败", e);
            return accessToken;
        }
       
    }
	public static String getTicket() {
        //微信令牌请求网址(由微信提供)
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+getAccess_token()+"&type=jsapi";
        String ticket = null;
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET");                                             // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");     // 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");        // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");//设置编码格式
            JSONObject demoJson = new JSONObject(message);
            ticket = demoJson.getString("ticket");
            is.close();
            System.out.println("ticket:"+ticket);
            return ticket;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取失败", e);
            return ticket;
        }
       
    }
	
}
