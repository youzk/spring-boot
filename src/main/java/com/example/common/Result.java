package com.example.common;

/**
 * 返回结果实体类
 * Result
 * 创建人:何雪平 
 * 时间：2017年7月14日-下午2:31:29 
 * @version 1.0.0
 *
 */
public class Result<T>{

	public boolean status = true;

	public String version = "1.0.0";
	
	private Integer result = ResponseEnum.SUCCESS.result();// 返回码

	private String desc = ResponseEnum.SUCCESS.desc();// 描述
	private T data;

	private String verificationCode; 
	public Result() {

	}
	
	public void setResult(ResponseEnum res,boolean status){
       this.result = res.result();
       this.desc = res.desc();
       this.status = status;
	}
	
    public void setResult(ResponseEnum res,boolean status,String version){
    	 this.result = res.result();
         this.desc = res.desc();
         this.status = status;
         this.version = version;
	}

	public Result(T t) {
		this.data = t;
	}


	public T getData() {
		return data;
	}


	public void setData(T data) {
		this.data = data;
	}

	public boolean isStatus() {
		return status;
	}


	public String getVersion() {
		return version;
	}


	public Integer getResult() {
		return result;
	}


	public String getDesc() {
		return desc;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	


}
