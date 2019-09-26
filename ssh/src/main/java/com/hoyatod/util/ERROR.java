package com.hoyatod.util;

public enum ERROR {
	ERR("error",500), SUC("success",200); 
	
	private String mess;
	private int status;
	
	// 构造方法  
	private ERROR(String mess, int status) {
		this.mess = mess;
		this.status = status;
	}


	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	
	
}
