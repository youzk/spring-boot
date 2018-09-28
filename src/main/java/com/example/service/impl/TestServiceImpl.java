package com.example.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.common.ResponseEnum;
import com.example.common.Result;
import com.example.dao.test1.TestDao;
import com.example.dao.test2.Test2Dao;
import com.example.domain.User;
import com.example.service.TestService;
import com.example.util.PasswordEntrypt;

@Service
public class TestServiceImpl implements TestService{
  
	@Autowired
	private  TestDao testDao;
	
	@Autowired
	private  Test2Dao test2Dao;
	
	
	@Override
	public String testMybatis() {
		Map<String, Object> map=new HashMap<>();
		map.put("userName", "yzk");
		map.put("passWord", "test");
		testDao.saveTest(map);
		test2Dao.saveTest2(map);
		return "测试";
	}
	
	/**
	 * 添加用户
	 * com.example.service.impl 
	 * 方法名：userAdd
	 * 创建人：yzk 
	 * 时间：2018年9月22日-下午3:45:37 
	 * @param user
	 * @return
	 * @see com.example.service.TestService#userAdd(com.example.domain.User)
	 * @exception 
	 * @since  1.0.0
	 */
	@Override
	public Result<String> userAdd(User user){
		Result<String> result=new Result<>();
		String passWord=user.getPassWord();
		//生成密码加密盐
		byte[] saltByte = PasswordEntrypt.generateSalt(8);
		String salt = Hex.encodeHexString(saltByte);
		String encryptPwd = PasswordEntrypt.sha1(passWord.getBytes(), saltByte);//密文
		user.setSalt(salt);
		user.setPassWord(encryptPwd);
		user.setState(1);
		int res=testDao.userAdd(user);
		if(res==0) {
			result.setStatus(false);
			result.setResult(ResponseEnum.FAIL_ADD.result());
			result.setDesc(ResponseEnum.FAIL_ADD.desc());			
		}
		return result;	
    }
	
	
}
