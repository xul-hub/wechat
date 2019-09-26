package com.mybatis.entity.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class UserDTO implements Serializable {
	private static final long serialVersionUID = 3314942757148640042L;
	
	private Integer id;
	private String username;
	private String password;
	private Timestamp createtime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", createtime=" + createtime
				+ "]";
	}
}
