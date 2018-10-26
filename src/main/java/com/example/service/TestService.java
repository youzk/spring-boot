package com.example.service;

import org.springframework.web.multipart.MultipartFile;

import com.example.common.Result;
import com.example.domain.User;

public interface TestService {
	
	
	String testMybatis();
	
	/**
	 * 添加用户
	 * com.example.service 
	 * 方法名：userAdd
	 * 创建人：yzk 
	 * 时间：2018年9月22日-下午3:43:12 
	 * @param user
	 * @return Result<String>
	 * @exception 
	 * @since  1.0.0
	 */
	Result<String> userAdd(User user);
	
	/**
	 * 上传文件处理
	 * com.example.service 
	 * 方法名：upload
	 * 创建人：yzk 
	 * 时间：2018年9月29日-下午4:37:13 
	 * @param file
	 * @return Result<String>
	 * @exception 
	 * @since  1.0.0
	 */
	Result<String> upload( MultipartFile file);
}
