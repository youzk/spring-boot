package com.example.common;


/**
 * 状态码管理
 * 
 * ResponseEnum
 * 时间：2016年11月8日-上午9:36:29 
 * @version 1.0.0
 *
 */
public enum  ResponseEnum {
	
	/*
	 * 系统参数
	 */
	SUCCESS(200, "OK"),
	FAIL_SYS_BAD_REQUEST(201, "错误的请求,参数不完整"),
	FAIL_SYS_API_NOT_FOUND(203, "访问了不存在的API"),
	FAIL_SYS_API_REJECT(204, "访问的API拒绝了对特定用户的请求"),
	FAIL_SYS_EXPIRED_REQUEST(205, "请求时间戳已失效"),
	FAIL_SYS_UNAUTHORIZED(206,  "app试图访问未授权的api"),
	FAIL_SYS_REQUEST_TIMEOUT(251,  "请求超时会话失效"),
	FAIL_SYS_SERVICE_FAULT(252,  "后端服务调用失败"),
	FAIL_SYS_SERVICE_NOT_EXIST(254, "服务器异常,API调用失败!"),
	FAIL_AUTH_NOT(255,  "你没有操作权限"),
	FAIL_SYS_ERROR(500,  "系统异常，系统不支持该请求方式"),
	FAIL_SERVER_ERROR(409,"系统业务异常"),
	FAIL_ADD(501,"添加失败"),
	EMPTY_FILE(502,"空文件!"),
	
	SMS_MAX_COUNT(101, "用户超出发送次数"),
	SMS_EXISTS(102,  "短信未失效"),
	SMS_FAIL(103,  "短信发送失败"),
	SMS_OK(100,"短信发送成功"),
	FAIL_ARGUMENT_ERROR(20002,  "非法参数异常"),
	FAIL_CONSTRAINT_ERROR(20003,  "违反约束异常"),
	FAIL_INVALID_ERROR(20004,  "无效的请求方式"),
	FAIL_DATABASE_ERROR(20005,  "数据库操作异常");
	private final int result;
    private final String desc;
    
    ResponseEnum(int result, String desc) {
        this.result = result;
        this.desc = desc;
    }
    
    public int result() {
        return result; 
    }

    public String desc() {
        return desc;
    }
    
}
