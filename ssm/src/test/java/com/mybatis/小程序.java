package com.mybatis;


import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.hoyatod.wechatclient.interfaces.SaasWechatGoodsInterface;
import com.hoyatod.wechatclient.protocol.wechat.SearchWechatGoodsRespData;
import com.mybatis.smallroutine.AccessToken;
import com.mybatis.smallroutine.AppletUser;
import com.mybatis.smallroutine.WechatAppletService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml", "classpath:spring-mvc.xml" })
public class 小程序 {
	
	@Autowired
	private WechatAppletService wechatAppletService;
	
	@Test
	public void test1() {
		AccessToken wechatAppletAccesstoken = wechatAppletService.getWechatAppletAccesstoken();
		System.out.println(wechatAppletAccesstoken);
	}
	
	@Test
	public void test2() {
		AppletUser appletUser = wechatAppletService.getWechatAppletUserInfoByJsCode("");
		System.out.println(appletUser);
	}
	
	@Autowired
	private SaasWechatGoodsInterface goodDao; 
	
	@Test
	public void views() {
		
		List<String> orderByFieldList =new ArrayList<String>();
		List<String> orderByTypeList =new ArrayList<String>();
		orderByFieldList.add("headlineReadnum");
		orderByFieldList.add("headlinePriceRatio");
		orderByFieldList.add("fansNum");
		orderByTypeList.add("desc");
		orderByTypeList.add("desc");
		orderByTypeList.add("desc");
		SearchWechatGoodsRespData listWechatGoodsDataByFuzzySearch = goodDao.listWechatGoodsDataByFuzzySearch("hoyatod", "hoyatod", "1",orderByFieldList,orderByTypeList, 1, 10, null, "127.0.0.1", true);
		System.out.println(listWechatGoodsDataByFuzzySearch.getTotalCount());
	}
}
