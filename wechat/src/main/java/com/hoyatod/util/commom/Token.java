package com.hoyatod.util.commom;

public class Token {

	private String access_token; // 获取到的凭证
	private int expires_in; // 凭证有效时间，单位：秒

	private String refresh_token;
	private String openid;
	private String scope;
	
	private String errcode;
	private String errmsg;
	
	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

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

	public String getRefresh_token() {
		return refresh_token;
	}

	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	@Override
	public String toString() {
		return "Token [access_token=" + access_token + ", expires_in=" + expires_in + ", refresh_token=" + refresh_token
				+ ", openid=" + openid + ", scope=" + scope + ", errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}
}
