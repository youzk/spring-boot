package com.example.dao.test1;

import java.util.List;
import java.util.Map;

import com.example.domain.Permission;
import com.example.domain.Role;
import com.example.domain.User;

public interface TestDao {
 
	
	int saveTest(Map<String, Object> map);
	
	/**
	 * 通过用户名获取此用户
	 * com.example.dao.test1 
	 * 方法名：getUserByUserName
	 * 创建人：yzk 
	 * 时间：2018年9月14日-下午3:27:38 
	 * @param userName
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	User getUserByUserName(String userName);
	
	
	/**
	 * 根据用户ID查询所有角色
	 * com.example.dao.test1 
	 * 方法名：getRoleListByUid
	 * 创建人：yzk 
	 * 时间：2018年9月14日-下午3:34:59 
	 * @param userId
	 * @return List<Role>
	 * @exception 
	 * @since  1.0.0
	 */
	List<Role> getRoleListByUid(int userId);
	
	/**
	 * 根据用户ID查询所有权限
	 * com.example.dao.test1 
	 * 方法名：getPermissionListByUid
	 * 创建人：yzk 
	 * 时间：2018年9月14日-下午3:37:39 
	 * @param userId
	 * @return List<Permission>
	 * @exception 
	 * @since  1.0.0
	 */
	List<Permission> getPermissionListByUid(int userId);
	
	/**
	 * 添加用户
	 * com.example.dao.test1 
	 * 方法名：userAdd
	 * 创建人：yzk 
	 * 时间：2018年9月22日-下午3:49:22 
	 * @param user
	 * @return int
	 * @exception 
	 * @since  1.0.0
	 */
	int userAdd(User user);
}
