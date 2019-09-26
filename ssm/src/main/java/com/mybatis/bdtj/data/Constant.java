package com.mybatis.bdtj.data;

public class Constant {
	
	/*
	 * 1. getSiteList 获取当前用户下的站点和子目录列表以及对应参数信息，不包括权限站点和汇总网站。
	 * 2. getData 根据站点ID获取站点报告数据。
	 */
    public static final String SITELIST = "https://api.baidu.com/json/tongji/v1/ReportService/getSiteList";
    public static final String DATA = "https://api.baidu.com/json/tongji/v1/ReportService/getData";
    
    /*
     * 账号 和 密码 以及token
     */
    public static final String USERNAME = "xxxxxxxxxxxxxx";
    public static final String PASSWORD = "xxxxxxxxxxxxxxxxxxx";
    public static final String TOKEN = "xxxxxxxxxxxxxxxxxxxxxxxxxxxx";
    
    /*
     * 配置参数
     */
    public static final String ACCOUNT_TYPE = "1";
    public static final String METHOD = "trend/time/a";
    public static final String METRICS = "pv_count,visitor_count,ip_count,bounce_ratio,avg_visit_time,avg_visit_pages";
}
