package com.mybatis.bdtj.login.api;

import java.security.Key;

import com.google.gson.Gson;
import com.mybatis.bdtj.login.entity.AppConstans;
import com.mybatis.bdtj.login.entity.DoLoginRequestImpl;
import com.mybatis.bdtj.login.entity.DoLoginResponse;
import com.mybatis.bdtj.login.util.GZipUtil;
import com.mybatis.bdtj.login.util.HttpClientUtils;
import com.mybatis.bdtj.login.util.RSAUtil;

public class BaiDuApi {
	
	/**
	 * 登陆接口
	 * 将登陆用户信息进行压缩加密，然后登陆
	 * @author xul
	 * @date 2016-11-15 下午01:08:44
	 */
	public static DoLoginResponse doLogin(String username,String password,String token) {
		try {
			DoLoginRequestImpl req = new DoLoginRequestImpl();
			req.setPassword(password);
			req.setUsername(username);
			req.setUuid(AppConstans.UUID);
			req.setToken(token);
			req.setFunctionName(AppConstans.LOGIN_METHOD);
			Key publicKey = RSAUtil.getPublicKey(AppConstans.KEY);
//			System.out.println("publicKey：" + publicKey );
			Gson gson = new Gson();
			String json = gson.toJson(req);
//			System.out.println("json:" + json);
			byte[] bytes = RSAUtil.encryptByPublicKey(GZipUtil.gzipString(json), publicKey);
			//System.out.println("zip:" + bytes.length);
			String loginResult =  HttpClientUtils.doHttpPost(AppConstans.HOME_LOGIN_ADDRESS, bytes, 10000, "utf-8");
			
			System.out.println(loginResult);
			DoLoginResponse resp = gson.fromJson(loginResult, DoLoginResponse.class);
			return resp;
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
}
