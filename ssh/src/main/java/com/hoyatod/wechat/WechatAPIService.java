package com.hoyatod.wechat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSON;
import com.hoyatod.util.HttpInvoker;

/**
 * 具体业务处理类
 */
public class WechatAPIService {
	
	private static Logger log = Logger.getLogger(WechatAPIService.class);
	
	 public static WechatData resolveXmlData(String xml) {
		 WechatData msg = null;  
	        try {  
	            if (xml.length() <= 0 || xml == null) {  
	                return null;  
	            }
	            Document document = DocumentHelper.parseText(xml); // 将字符串转化为XML文档对象  
	            Element root = document.getRootElement();  // 获得文档的根节点
	            Iterator<?> iter = root.elementIterator();  //遍历根节点下所有子节点  
	              
	            msg = new WechatData(); 
	            
	            //利用反射机制，调用set方法   获取该实体的元类型com.hoyatod.wechat
	            Class<?> c = Class.forName("com.hoyatod.wechat.WechatData");  
	            msg = (WechatData)c.newInstance();                                           //创建实体对象  
	              
	            while(iter.hasNext()){  
	                Element ele = (Element)iter.next(); 
	                Field field = c.getDeclaredField(ele.getName());                           //获取set方法中的参数字段（实体类的属性）  
	                Method method = c.getDeclaredMethod("set"+ele.getName(), field.getType()); //获取set方法，field.getType())获取它的参数数据类型    
	                method.invoke(msg, ele.getText());                                         //调用set方法  
	            }
	            return msg;  
	        } catch (Exception e) {  
	            log.error("格式异常", e);
	            return null;
	        }  
	 }
	 
	 public static String getXmlData(HttpServletRequest request) {
		try {
			StringBuffer sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
			String s = "";
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}
			String xml = sb.toString();  //次即为接收到微信端发送过来的xml数据
			return xml;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	 }
	 
	 public static List<SubscribeList> findFocusersList(){
		 final String openidList = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";
		 final String next_openidList = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";
		 List<SubscribeList> subscribeList = new ArrayList<SubscribeList>();
		 try {
			String openidListStr = HttpInvoker.httpGet(openidList.replace("ACCESS_TOKEN",WechatTools.getAccess_token()), null);
			SubscribeList firstSubscribe = JSON.parseObject(openidListStr, SubscribeList.class);
			System.out.println("firstSubscribe:"+firstSubscribe);
			if(firstSubscribe != null) {
				boolean flag = true;
				String next_openid = firstSubscribe.getNext_openid();
				subscribeList.add(firstSubscribe);
				do {
					String next_openidListStr = HttpInvoker.httpGet(next_openidList.replace("ACCESS_TOKEN",WechatTools.getAccess_token().replace("NEXT_OPENID", next_openid)), null);
					SubscribeList nextSubscribe = JSON.parseObject(next_openidListStr, SubscribeList.class);
					if(nextSubscribe == null || nextSubscribe.getErrcode() != null || nextSubscribe.getErrmsg() !=null) {
						flag = false;
					}else {
						flag = true;
						next_openid = nextSubscribe.getNext_openid();
						subscribeList.add(nextSubscribe);
					}
				} while (flag);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return subscribeList;
	 }
	 
	 public static void main(String[] args) {
		List<SubscribeList> findFocusersList = findFocusersList();
		for (SubscribeList subscribeList : findFocusersList) {
			Data data = subscribeList.getData();
			List<String> openidList = data.getOpenid();
			for (String openid : openidList) {
				System.out.println(openid);
			}
		}
	}
}




































