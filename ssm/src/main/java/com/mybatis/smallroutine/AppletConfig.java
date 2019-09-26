package com.mybatis.smallroutine;

public class AppletConfig {
	//小程序基本sdk
	public final static String APPLET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	public final static String APPLET_GETUSERINFO = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";
	public final static String APPLET_CODE = "https://api.weixin.qq.com/cgi-bin/wxaapp/createwxaqrcode?access_token=ACCESS_TOKEN";
	public final static String APPLET_TEMPLATE_NEWS = "https://api.weixin.qq.com/cgi-bin/message/wxopen/template/send?access_token=ACCESS_TOKEN";
	public final static String APPLET_MA = "http://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=ACCESS_TOKEN";
	//用户支付完成后，获取该用户的 UnionId，无需用户授权
	public final static String getPaidUnionId = "https://api.weixin.qq.com/wxa/getpaidunionid?access_token=ACCESS_TOKEN&openid=OPENID";
	
}
