package com.example.domain;

import java.io.Serializable;

public class Role implements Serializable{

	
	private static final long serialVersionUID = 993040797864117377L;
	
	private int id;//角色id
	
	private String role;//角色名称
	
	private String description;//角色描述
	
	private int stcode;//角色状态 1为启用 0为禁用

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStcode() {
		return stcode;
	}

	public void setStcode(int stcode) {
		this.stcode = stcode;
	}
	
	

}
