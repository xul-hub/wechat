package com.mybatis.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
	
	public static boolean checkStrIsNull(String strData) {
		if(strData == null) {
			return false;
		}
		if(strData.trim().equals("") || strData.trim().equals("null") || strData.trim().equals("undefined")) {
			return false;
		}
		return true;
	}
	
	public static boolean checkObjIsNull(Object obj) {
		return obj == null? false : true;
	}
	
	public static boolean checkPhoneNo(String phone) {
		Pattern p = Pattern.compile("^[1](([3][0-9])|([4][5,7,9])|([5][^4,6,9])|([6][6])|([7][3,5,6,7,8])|([8][0-9])|([9][8,9]))[0-9]{8}$");  
		Matcher matcher = p.matcher(phone);  
		return matcher.matches();  
	}
	
	public static boolean checkRegularExpression(String data,String regExp) {
		if(!checkStrIsNull(data) && !checkStrIsNull(regExp)) {
			return false;
		}
		Pattern p = Pattern.compile(regExp);
		Matcher matcher = p.matcher(data);
		return matcher.find();
	}
	
	 public static String getIpAddr(HttpServletRequest request){  
	        String ipAddress = request.getHeader("x-forwarded-for");  
	            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	                ipAddress = request.getHeader("Proxy-Client-IP");  
	            }  
	            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	                ipAddress = request.getHeader("WL-Proxy-Client-IP");  
	            }  
	            if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {  
	                ipAddress = request.getRemoteAddr();  
	                if(ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")){  
	                    //根据网卡取本机配置的IP  
	                    InetAddress inet=null;  
	                    try {  
	                        inet = InetAddress.getLocalHost();
	                    } catch (UnknownHostException e) {  
	                        e.printStackTrace();  
	                    }  
	                    ipAddress= inet.getHostAddress();  
	                }  
	            }  
	            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割  
	            if(ipAddress!=null && ipAddress.length()>15){ //"***.***.***.***".length() = 15  
	                if(ipAddress.indexOf(",")>0){  
	                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));  
	                }  
	            }  
	            return ipAddress;   
	    }
	public static void main(String[] args) {
		System.out.println(checkRegularExpression(null,null));
		System.out.println(checkPhoneNo("18897899920"));
	}
	
	
}













