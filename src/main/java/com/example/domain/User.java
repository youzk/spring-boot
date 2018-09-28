package com.example.domain;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 318278932974580793L;
	
	private int uid;//用户id
	
	private String userName;//用户名
	
	private String name;//姓名
	
	private String passWord;//密码
	
	private String salt;//加密密码的盐
	
	private int state;//用户状态 1:正常状态,0：用户被锁定

	
	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	

}
