package com.mybatis.utils.https;

public class HttpsUrl {
	
	//淘宝查询电话号码归属地（可用）
	public static final String a = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=手机号";
	
	//百付宝接口（可用） 
	public static final String b = "https://www.baifubao.com/callback?cmd=1059&callback=phone&phone=手机号";
	
	//快递接口: ps:快递公司编码:申通=”shentong” EMS=”ems” 顺丰=”shunfeng” 圆通=”yuantong” 中通=”zhongtong” 韵达=”yunda” 天天=”tiantian” 汇通=”huitongkuaidi” 全峰=”quanfengkuaidi” 德邦=”debangwuliu” 宅急送=”zhaijisong”
	public static final String c = "http://www.kuaidi100.com/query?type=快递公司代号&postid=快递单号";

	//谷歌接口（不确定是否可用） FeedXml转json接口: 备选参数:callback：&callback=foo就会在json外面嵌套foo({})方便做jsonp使用。备选参数:n：返回多少条记录。
	public static final String d = "http://ajax.googleapis.com/ajax/services/feed/load?q=Feed地址&v=1.0";

	//QQ空间音乐接口（可用）
	public static final String e = "http://qzone-music.qq.com/fcg-bin/cgi_playlist_xml.fcg?uin=QQ号码&json=1&g_tk=1916754934";

	//QQ空间收藏音乐接口（可用）
	public static final String f = "http://qzone-music.qq.com/fcg-bin/fcg_music_fav_getinfo.fcg?dirinfo=0&dirid=1&uin=QQ号&p=0.519638272547262&g_tk=1284234856";

	//多米音乐接口（可用）
	public static final String g = "http://v5.pc.duomi.com/search-ajaxsearch-searchall?kw=关键字&pi=页码&pz=每页音乐数";

	//soso接口（可用）
	public static final String h = "http://cgi.music.soso.com/fcgi-bin/fcg_search_xmldata.q?source=10&w=关键字&perpage=1&ie=utf-8";

	//地图接口 阿里云根据地区名获取经纬度接口（可用）参数解释: 纬度,经度type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)
	public static final String i = "http://gc.ditu.aliyun.com/geocoding?a=苏州市";

	//阿里云根据经纬度获取地区名接口（可用）
	public static final String j = "http://gc.ditu.aliyun.com/regeocoding?l=39.938133,116.395739&type=001";

	//IP接口 新浪接口(ip值为空的时候 获取本地的)（可用）
	public static final String k = "http://int.dpool.sina.com.cn/iplookup/iplookup.php?format=json&ip=218.4.255.255";
	
	//手机信息查询接口 淘宝网接口（可用）
	public static final String l = "http://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=CODE";

	//获取QQ昵称和用户头像(可用，不过会提示登录) 参数 qq号码
	public static final String m = "http://r.qzone.qq.com/cgi-bin/user/cgi_personal_card?uin=QQ";

}
