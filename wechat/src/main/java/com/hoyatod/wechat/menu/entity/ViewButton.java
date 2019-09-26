package com.hoyatod.wechat.menu.entity;

public class ViewButton extends Button {

	private String type;// 菜单类型
	private String url;// view路径值

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
