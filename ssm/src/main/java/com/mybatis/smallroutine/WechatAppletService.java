package com.mybatis.smallroutine;

import java.io.IOException;

import org.springframework.stereotype.Component;

@Component
public class WechatAppletService {
	
	/**
	 * 
	* @Description: 获取小程序调用接口凭证Accesstoken
	*
	* @return：AccessToken
	* @throws：Exception
	*
	* @version: v1.0.0
	* @author: xul
	* @date: 2019年4月10日 上午10:57:33
	 */
	public AccessToken getWechatAppletAccesstoken(){
		try {
			String doJsonStr = OkhttpUtil.doGetHttpRequest(AppletConfig.APPLET_ACCESS_TOKEN.replace("APPID",AppletConstants.APPID).replace("APPSECRET", AppletConstants.SECRET));
			AccessToken token = JacksonUtil.str2Obj(doJsonStr, AccessToken.class);
			return token;
		} catch (IOException e) {
			// TODO: handle exception
			return null;
		}
	}
	
	/**
	 * 
	* @Description: 获取小城的openid
	*
	* @param:jsCode
	* @return：AppletUser
	* @throws：IOException
	*
	* @version: v1.0.0
	* @author: xul
	* @date: 2019年4月11日 上午9:27:10
	 */
	public AppletUser getWechatAppletUserInfoByJsCode(String jsCode){
		try {
			String doJsonStr = OkhttpUtil.doGetHttpRequest(AppletConfig.APPLET_GETUSERINFO.replace("APPID", AppletConstants.APPID).replace("SECRET", AppletConstants.SECRET).replace("JSCODE", jsCode));
			AppletUser u = JacksonUtil.str2Obj(doJsonStr, AppletUser.class);
			return u;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
}
