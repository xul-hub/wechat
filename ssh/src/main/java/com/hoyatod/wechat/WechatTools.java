package com.hoyatod.wechat;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;


public class WechatTools {
	
	private static Logger log = Logger.getLogger(WechatTools.class);
	
	public static String getAccess_token() {
        //微信令牌请求网址(由微信提供)
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + Constants.APPID + "&secret=" + Constants.APPSECRET;
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
            http.setRequestMethod("GET");                                         // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");  // 读取超时30秒
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
	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
	   String data = "{\"code\":\"0\",\"msg\":\"你好\",\"data\":[{\"agencycode\":\"200\",\"agencyname\":\"haha\",\"agencystr\":\"ccccc\"},{\"agencycode\":\"wuji\",\"agencyname\":\"bbb\",\"agencystr\":\"aaa\"}]}";
       ObjectMapper objectMapper= new ObjectMapper();
       DataAll datas = objectMapper.readValue(data, DataAll.class);
       System.out.println(datas);
	}
	
	public static String httpGet() {
        //微信令牌请求网址(由微信提供)
        String url = "http://219.143.245.173:80/cif/IndexFundTra?method=flash_xsjg";
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET");                                           // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");   // 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");     // 读取超时30秒
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");//设置编码格式
            is.close();
            return message;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("获取失败", e);
            return null;
        }
       
    }
}
