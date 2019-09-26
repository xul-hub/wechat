package com.mybatis.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class SysLogDTO implements Serializable {

	private static final long serialVersionUID = 8874164589614737167L;

	private Integer id;
	private Integer logType;
	private String username;
	private String clientIp;
	private Timestamp createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "SysLogDTO [id=" + id + ", logType=" + logType + ", username=" + username + ", clientIp=" + clientIp
				+ ", createtime=" + createtime + "]";
	}
}
