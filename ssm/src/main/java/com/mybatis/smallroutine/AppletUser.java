package com.mybatis.smallroutine;

import java.io.Serializable;

public class AppletUser implements Serializable{
	
	// @Fields serialVersionUID : TODO
	private static final long serialVersionUID = -4248736348027583593L;
	
	private String openid;          // 用户唯一标识
	private String session_key;     // 会话密钥
	private String unionid;			//用户在开放平台的唯一标识符，在满足 UnionID 下发条件的情况下会返回，详见 UnionID 机制说明。
	/**
	 *	-1	         系统繁忙，此时请开发者稍候再试	
	 *	0	         请求成功	
	 *	40029	code 无效	
	 *	45011	频率限制，每个用户每分钟100次 
	 */
	private String errcode;         // 错误码
	private String errmsg;          // 错误信息
	
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getSession_key() {
		return session_key;
	}
	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AppletUser [openid=" + openid + ", session_key=" + session_key + ", unionid=" + unionid + ", errcode="
				+ errcode + ", errmsg=" + errmsg + "]";
	}
}
