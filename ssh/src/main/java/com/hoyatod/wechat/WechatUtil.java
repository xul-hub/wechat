package com.hoyatod.wechat;

import java.util.Date;

import org.apache.log4j.Logger;

/** 
 * TODO 解析接收到的微信xml，返回消息对象 
 * @author xul 
 * 
 */  
public class WechatUtil {
	
	private static Logger log = Logger.getLogger(WechatUtil.class);
		
	/**
	 * 发送文本信息
	 * 
	 * @param to
	 * @param from
	 * @param content
	 * @return String
	 */
	public static String formatXmlAnswer(String to, String from, String content) {
		try {
			StringBuffer sb = new StringBuffer();
			Date date = new Date();
			sb.append("<xml><ToUserName><![CDATA[");
			sb.append(to);
			sb.append("]]></ToUserName><FromUserName><![CDATA[");
			sb.append(from);
			sb.append("]]></FromUserName><CreateTime>");
			sb.append(date.getTime());
			sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");
			sb.append(content);
			sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");
			return sb.toString();
		} catch (Exception e) {
			log.error("转换失败", e);
			return null;
		}
	}
	
	/**
	 * 发送图片信息
	 * 
	 * @param to
	 * @param from
	 * @param content
	 * @return String
	 */
	public static String formatXmlImage(String to, String from, Image image) {
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<xml><ToUserName><![CDATA[");
			sb.append(to);
			sb.append("]]></ToUserName><FromUserName><![CDATA[");
			sb.append(from);
			sb.append("]]></FromUserName><CreateTime>");
			sb.append(image.getCreateTime());
			sb.append("</CreateTime><MsgType><![CDATA[image]]></MsgType><PicUrl><![CDATA[");
			sb.append(image.getPicUrl());
			sb.append("]]></PicUrl><MsgId><![CDATA[");
			sb.append(image.getMsgId());
			sb.append("]]></MsgId><MediaId><![CDATA[");
			sb.append(image.getMediaId());
			sb.append("]]></MediaId></xml>");
			return sb.toString();
		} catch (Exception e) {
			log.error("转换失败", e);
			return null;
		}
	}
	/**
	 * 发送语音消息
	 * 
	 * @param to
	 * @param from
	 * @param voice
	 * @return String
	 */
	
	public static String formatXmlVoice(String to, String from, Voice voice) {
		try {
			StringBuffer sb = new StringBuffer();
			Date date = new Date();
			sb.append("<xml><ToUserName><![CDATA[");
			sb.append(to);
			sb.append("]]></ToUserName><FromUserName><![CDATA[");
			sb.append(from);
			sb.append("]]></FromUserName><CreateTime>");
			sb.append(date.getTime());
			sb.append("</CreateTime><MsgType><![CDATA[voice]]></MsgType><Voice>");
			sb.append("<MediaId><![CDATA[");
			sb.append(voice.getMediaId());
			sb.append("]]></MediaId></Voice></xml>");
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}
	
	/**
	 * 发送视频消息
	 * 
	 * @param to
	 * @param from
	 * @param music  
	 * @return String
	 */
	public static String formatXmlVideo(String to, String from, Video video) {
		try {
			StringBuffer sb = new StringBuffer();
			Date date = new Date();
			sb.append("<xml><ToUserName><![CDATA[");
			sb.append(to);
			sb.append("]]></ToUserName><FromUserName><![CDATA[");
			sb.append(from);
			sb.append("]]></FromUserName><CreateTime>");
			sb.append(date.getTime());
			sb.append("</CreateTime><MsgType><![CDATA[video]]></MsgType><video>");
			sb.append("<MediaId><![CDATA[");
			sb.append(video.getMediaId());
			sb.append("]]></MediaId><Title><![CDATA[");
			sb.append(video.getTitle());
			sb.append("]]></Title><Description><![CDATA[");
			sb.append(video.getDescription());
			sb.append("]]></Description></video></xml>");
			return sb.toString();
		} catch (Exception e) {
			log.error("系统异常",e);
			return null;
		}
	}
	
	/**
	 * 发送音乐消息
	 * 
	 * @param to
	 * @param from
	 * @param music  
	 * @return String
	 */
	public static String formatXmlMusic(String to, String from, Music music) {
		try {
			StringBuffer sb = new StringBuffer();
			Date date = new Date();
			sb.append("<xml><ToUserName><![CDATA[");
			sb.append(to);
			sb.append("]]></ToUserName><FromUserName><![CDATA[");
			sb.append(from);
			sb.append("]]></FromUserName><CreateTime>");
			sb.append(date.getTime());
			sb.append("</CreateTime><MsgType><![CDATA[music]]></MsgType><Music>");
			sb.append("<Title><![CDATA[");
			sb.append(music.getTitle());
			sb.append("]]></Title><Description><![CDATA[");
			sb.append(music.getDescription());
			sb.append("]]></Description><MusicUrl><![CDATA[");
			sb.append(music.getMusicUrl());
			sb.append("]]></MusicUrl><HQMusicUrl><![CDATA[");
			sb.append(music.getHQMusicUrl());
			sb.append("]]></HQMusicUrl></Music></xml>");
			return sb.toString();
		} catch (Exception e) {
			log.error("系统异常",e);
			return null;
		}
	}
	
	/**
	 * 发送图文消息
	 * 
	 * @param to
	 * @param from
	 * @param articles对象
	 * @return
	 */
	public static String formatXmlImgeText(String to, String from, Articles articles) {
		try {
			StringBuffer sb = new StringBuffer();
			Date date = new Date();
			sb.append("<xml><ToUserName><![CDATA[");
			sb.append(to);
			sb.append("]]></ToUserName><FromUserName><![CDATA[");
			sb.append(from);
			sb.append("]]></FromUserName><CreateTime>");
			sb.append(date.getTime());
			sb.append("</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount><![CDATA[");
			sb.append(articles.getItemList().size());
			sb.append("]]></ArticleCount><Articles>");                                                                                                                                                                                                                                                                                                                                                       
			for (int i = 0; i < articles.getItemList().size(); i++) {
				sb.append("<item><Title><![CDATA[");
				sb.append(articles.getItemList().get(i).getTitle());
				sb.append("]]></Title><Description><![CDATA[");
				sb.append(articles.getItemList().get(i).getDescription());
				sb.append("]]></Description><PicUrl><![CDATA[");
				sb.append(articles.getItemList().get(i).getPicUrl());
				sb.append("]]></PicUrl><Url><![CDATA[");
				sb.append(articles.getItemList().get(i).getUrl());
				sb.append("]]</Url></item>");
			}
			sb.append("</Articles><FuncFlag>0</FuncFlag></xml>");
			return sb.toString();
		} catch (Exception e) {
			log.error("转换失败", e);
			return null;
		}
	}
}
