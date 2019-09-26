package com.mybatis.bdtj.data;

public class SiteListDTO {
	
	private String create_time;
	private String domain;
	private int site_id;
	private int status;
	
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	
	public int getSite_id() {
		return site_id;
	}
	public void setSite_id(int site_id) {
		this.site_id = site_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "站点列表: [时间：" + create_time + ", 站点链接:" + domain + ", 站点ID:" + site_id + ", 状态:"
				+ status + "] \n";
	}
	
	
}
