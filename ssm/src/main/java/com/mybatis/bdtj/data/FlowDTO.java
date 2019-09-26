package com.mybatis.bdtj.data;

public class FlowDTO {
	
	private Integer pv;//浏览量(PV)	
	private Integer pu;//访客数(UV)
	private Integer ip;//ip個數
	
	private Integer bounce_ratio;//所查询指标为跳出率
	private Integer avg_visit_time;//平均访问时长分
	private Integer avg_visit_pages;//平均访问页数
	
	private Integer status;  // 0 -1
	
	
	public FlowDTO() {
		super();
	}
	public FlowDTO(Integer status) {
		super();
		this.status = status;
	}
	public Integer getPv() {
		return pv;
	}
	public void setPv(Integer pv) {
		this.pv = pv;
	}
	public Integer getPu() {
		return pu;
	}
	public void setPu(Integer pu) {
		this.pu = pu;
	}
	public Integer getIp() {
		return ip;
	}
	public void setIp(Integer ip) {
		this.ip = ip;
	}
	public Integer getBounce_ratio() {
		return bounce_ratio;
	}
	public void setBounce_ratio(Integer bounce_ratio) {
		this.bounce_ratio = bounce_ratio;
	}
	public Integer getAvg_visit_time() {
		return avg_visit_time;
	}
	public void setAvg_visit_time(Integer avg_visit_time) {
		this.avg_visit_time = avg_visit_time;
	}
	public Integer getAvg_visit_pages() {
		return avg_visit_pages;
	}
	public void setAvg_visit_pages(Integer avg_visit_pages) {
		this.avg_visit_pages = avg_visit_pages;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "FlowDTO [pv=" + pv + ", pu=" + pu + ", ip=" + ip + ", bounce_ratio=" + bounce_ratio
				+ ", avg_visit_time=" + avg_visit_time + ", avg_visit_pages=" + avg_visit_pages + ", status=" + status
				+ "]";
	}
}
