package com.mybatis.bdtj.login.test;

import com.mybatis.bdtj.login.api.BaiDuApi;
import com.mybatis.bdtj.login.entity.DoLoginResponse;

public class LoginTest {

	public static void main(String[] args) {
		
		String username = "xxxxx";		//百度统计账号
		String password = "xxxxx";		//百度统计密码
		String token = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx";					//百度统计token
		
		DoLoginResponse response = BaiDuApi.doLogin(username, password, token);
		System.out.println(response);
//		System.out.println(response.getUcid());
//		System.out.println(response.getQuestions());
		
	}
}
