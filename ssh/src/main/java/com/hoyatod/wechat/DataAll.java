package com.hoyatod.wechat;

import java.util.List;

public class DataAll {
	
	private String code;
	private String msg;
	private List<Data> data;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "DataAll [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}


}
