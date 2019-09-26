package com.hoyatod.util;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 【匹配度可以，速度较慢】
 * @author xuliang
 *
 */
public class KeyWordFilter {
	
	private static Logger log = Logger.getLogger(KeyWordFilter.class);
	private static Pattern pattern = null;
	private static int keywordsCount = 0;

	// 从words.properties初始化正则表达式字符串
	public static void initPattern() {
		StringBuffer patternBuffer = new StringBuffer();
		try {
			//words.properties
			InputStream in = KeyWordFilter.class.getClassLoader().getResourceAsStream("keywords.properties");
			Properties property = new Properties();
			property.load(in);
			Enumeration<?> enu = property.propertyNames();
			patternBuffer.append("(");
			while (enu.hasMoreElements()) {
				String scontent = (String) enu.nextElement();
				patternBuffer.append(scontent + "|");
				//System.out.println(scontent);
				
				log.debug("scontent:" + scontent);
				keywordsCount ++;
			}
			patternBuffer.deleteCharAt(patternBuffer.length() - 1);
			patternBuffer.append(")");
			//System.out.println(patternBuffer);
			
			// unix换成UTF-8
			// pattern = Pattern.compile(new String(patternBuf.toString().getBytes("ISO-8859-1"), "UTF-8"));
			
			// win下换成GB2312
			// pattern = Pattern.compile(new String(patternBuf.toString().getBytes("ISO-8859-1"), "gb2312"));
			
			// 装换编码
			pattern = Pattern.compile(patternBuffer.toString());
		} catch (Exception e) {
			log.error("异常.", e);
			e.printStackTrace();
		}
	}

	public static String doFilter(String str) {
		Matcher m = pattern.matcher(str);
		while (m.find()) {// 查找符合pattern的字符串
			log.error("The result is here :" + m.group());
		}
		// 选择替换方式，这里以* 号代替
		str = m.replaceAll("*");
		return str;
	}

	public static void main(String[] args) {
		long startNumer = System.currentTimeMillis(); 
		initPattern();
//		String str = "我日，艹，fuck，你妹的 干啥呢";
		log.error("敏感词的数量：" + keywordsCount);
		String str = "太多的伤yuming感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。";
//	            + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"  
//	            + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"  
//	            + "然后法轮功 我们的扮演的角色就是跟随着主人yum公的喜红客联盟 怒哀20哀2015/4/16 20152015/4/16乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"  
//	            + "关, 人, 流, 电, 发, 情, 太, 限, 法轮功, 个人, 经, 色, 许, 公, 动, 地, 方, 基, 在, 上, 红, 强, 自杀指南, 制, 卡, 三级片, 一, 夜, 多, 手机, 于, 自，"  
//	            + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";  
//		String str = "";
		log.error("被检测字符串长度:"+str.length()); 
		str = doFilter(str);
		
		long endNumber = System.currentTimeMillis();  
		log.error("总共耗时:"+(endNumber-startNumer)+"ms,"+"替换后的字符串为:\n"+str); 
	}
}
