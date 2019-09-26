package com.hoyatod.wechat;

public class SubscribeList {
	private Integer total;
	private Integer count;
	private Data data;
	private String next_openid;
	
	private Integer errcode;
	private String errmsg;
	
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	@Override
	public String toString() {
		return "SubscribeList [total=" + total + ", count=" + count + ", data=" + data + ", next_openid=" + next_openid
				+ ", errcode=" + errcode + ", errmsg=" + errmsg + "]";
	}
}
