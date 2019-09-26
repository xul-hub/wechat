package com.mybatis.bdtj.login.entity;

import java.sql.Timestamp;

/**
 * <p>Title:系统配置常量 </p>
 * <p>Description: </p>
 * <p>Company:rhhz </p> 
 * @author shy
 * @date 2016-11-15 上午10:16:22
 */
public class AppConstans {
	
	public static final String HOME_LOGIN_ADDRESS = "https://api.baidu.com/sem/common/HolmesLoginService";
	public static final String QUERY_ADDRESS = "https://api.baidu.com/json/tongji/v1/ProductService/api";
	public static final String GET_SITE_LIST = "https://api.baidu.com/json/tongji/v1/ReportService/getSiteList";
	public static final String GET_SITE_DATA = "https://api.baidu.com/json/tongji/v1/ReportService/getData";
	public static final int MAX_MSG_SIZE = 4096;
	public static final Integer SC_OK = 200;
	public static final String KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDHn/hfvTLRXViBXTmBhNYEIJeGGGDkmrYBxCRelriLEYEcrwWrzp0au9nEISpjMlXeEW4+T82bCM22+JUXZpIga5qdBrPkjU08Ktf5n7Nsd7n9ZeI0YoAKCub3ulVExcxGeS3RVxFai9ozERlavpoTOdUzEH6YWHP4reFfpMpLzwIDAQAB";
    public static final String UUID = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBg-1";//MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBg-1  00-00-00-00-00-00-00-E0dd
	public static final String ENCODING = "UTF-8";
	public static final String KEY_ALGORITHM = "RSA";
	
	public static final String LOGIN_METHOD = "doLogin";
	public static final String PROFILE = "profile";
	public static final String METHOD_GET_SITES = "getsites";
	public static final String REPORT = "report";
	public static final String METHOD_QUERY = "query";
	public static final String METHOD_STATUS = "getstatus";
	
	public static final String GRAN_DAY = "day";
	public static final String GRAN_MONTH = "month";
	public static final String DEFAULT_START_DATE = "20100101";
	public static final String DEFAULT_DATE_PATTERN = "yyyyMMdd";
	
	/**查询指标
	 *  必填
	 *	取值范围：
	 *	pageviews:浏览量（PV）
	 *	visitors:访客数（UV）
	 *	ips:IP数
	 *	entrances:入口页次数
	 *	outwards:贡献下游流量
	 *	exits:退出页次数
	 *	stayTime:平均停留时长
	 *	exitRate:退出率
	 */
	public static final String METRICS = "pv_count,visitor_count,ip_count,new_visitor_count";
	
	/**趋势数据*/
	//public static final String METHOD_TIME = "overview/getTimeTrendRpt";//此方法只能查询单个指标
	public static final String METHOD_TIME = "trend/time/a";
	
	/**区域分布*/
	public static final String METHOD_DISTRICT = "overview/getDistrictRpt";
	
	/**来源网站、搜索词、 入口页面、受访页面*/
	public static final String METHOD_COMMON = "overview/getCommonTrackRp";
	
	/**
	 * 当前时间
	 * @return Timestamp 类型
	 */
	public static Timestamp nowTimestamp(){
		return new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * 按照指定格式格式化时间
	 * @param format
	 * @param time
	 * @return
	 * @author shy
	 * @date 2017-1-11 下午01:49:50
	 */
	public static String dateTimeFormat(String format,Timestamp time){
		return (new java.text.SimpleDateFormat(format)).format(time);
	}
	
	
	
}
