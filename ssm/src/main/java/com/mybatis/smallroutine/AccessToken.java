package com.mybatis.smallroutine;

import java.io.Serializable;

public class AccessToken implements Serializable{
	
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -7074160547002194248L;
	
	public String access_token;	//string	获取到的凭证
	public int expires_in;		//number    凭证有效时间，单位：秒。目前是7200秒之内的值。
	
	/**
	 *  0	请求成功	
	 *  -1	系统繁忙，此时请开发者稍候再试	
	 *	40001	AppSecret 错误或者 AppSecret 不属于这个小程序，请开发者确认 AppSecret 的正确性	
	 *	40002	请确保 grant_type 字段值为 client_credential	
	 *	40013	不合法的 AppID，请开发者检查 AppID 的正确性，避免异常字符，注意大小写
	 */
	public int errcode;			//number	错误码
	public String errmsg;       //string	错误信息
	
	
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public int getErrcode() {
		return errcode;
	}
	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AccessToken [access_token=" + access_token + ", expires_in=" + expires_in + ", errcode=" + errcode
				+ ", errmsg=" + errmsg + "]";
	}
}
