package com.example.domain;

import java.io.Serializable;

public class Permission implements Serializable{

	
	private static final long serialVersionUID = 7476468878870253216L;
	
	private int id;//权限id
	
	private String name;//权限名称
	
	private String resourceType;//资源类型，[menu|button]
	
	private String url;//资源路径
	
	private String permission;//权限字符串,menu例子：role:*，button例子：role:create,role:update,role:delete,role:view
	
	private int parentId;//父编号
	
	private String parentIds;//父编号列表
	
	private int stcode;//权限状态 1为启用 0为禁用

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceType() {
		return resourceType;
	}

	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getParentIds() {
		return parentIds;
	}

	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	public int getStcode() {
		return stcode;
	}

	public void setStcode(int stcode) {
		this.stcode = stcode;
	}
	

}
