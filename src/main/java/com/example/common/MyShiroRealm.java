package com.example.common;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.dao.test1.TestDao;
import com.example.domain.Permission;
import com.example.domain.Role;
import com.example.domain.User;
import com.example.util.PasswordEntrypt;


import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MyShiroRealm extends AuthorizingRealm {
	
	@Autowired
    private TestDao testDao;
	
	@Autowired
	private RedisComponent redisComponent;

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     * com.example.config 
     * 方法名：doGetAuthenticationInfo
     * 创建人：yzk 
     * 时间：2018年9月13日-下午4:34:12 
     * @param authcToken
     * @return
     * @throws AuthenticationException
     * @see org.apache.shiro.realm.AuthenticatingRealm#doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken)
     * @exception 
     * @since  1.0.0
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
            throws AuthenticationException {
        System.out.println("登录认证-->MyShiroRealm.doGetAuthenticationInfo()");
        
        UsernamePasswordToken  token = (UsernamePasswordToken) authcToken;
    	User user = testDao.getUserByUserName(token.getUsername());//通过用户名获取此用户
    	if (user==null) {
    		throw new UnknownAccountException("账号不存在!");
    	}
    	else {
    		String salt=user.getSalt();
    		String passWord=user.getPassWord();
    		byte[] saltByte = null;
			try {
				saltByte = Hex.decodeHex(salt.toCharArray());
			} catch (DecoderException e) {
				throw new IncorrectCredentialsException("解码错误!");
			}
    		//从token取出来的密码是字符数组，需要转换成字符串
    		//sha1加密与md5加密一样都是不可逆的,所以如果用户忘记密码的话只能重置密码
    		if (!passWord.equals(PasswordEntrypt.sha1(new String(token.getPassword()).getBytes(), saltByte))) {
    			throw new IncorrectCredentialsException("密码不正确!");
    		}
    		else 
    			if(user.getState()==0) {
    			   throw new DisabledAccountException("帐号已经被锁定!");
    		    }
    	}   
    	return new SimpleAuthenticationInfo(user, new String(token.getPassword()), getName());
    }
    
    
    /**
     * 授权
     * com.example.config 
     * 方法名：doGetAuthorizationInfo
     * 创建人：yzk 
     * 时间：2018年9月13日-下午4:34:41 
     * @param principals
     * @return
     * @see org.apache.shiro.realm.AuthorizingRealm#doGetAuthorizationInfo(org.apache.shiro.subject.PrincipalCollection)
     * @exception 
     * @since  1.0.0
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
    	SimpleAuthorizationInfo info =  new SimpleAuthorizationInfo();
        User user = (User)SecurityUtils.getSubject().getPrincipal();
    	int userId = user.getUid();
    	String key1=userId+":role";
    	String key2=userId+":permission";
        /* 每次访问有权限的链接都会去执行MyShiroRealm.doGetAuthorizationInfo()方法来查询当前用户的权限，
    	因为实际情况中权限是不会经常变得，这样就可以使用redis进行权限的缓存*/
    	if(redisComponent.hasKey(key1)) {
    		Set<Set<String>> set=redisComponent.getSetValue(key1);
    		for (Set<String> set1 : set) {//因为在set方法里做了限制所以只会有一个集合
    			info.setRoles(set1);
			}
    	}
    	else {
    	List<Role> roleList = testDao.getRoleListByUid(userId);//根据用户ID查询所有角色（role)
    	Set<String> roleSet = new HashSet<String>();
    	for(Role role : roleList){
    		roleSet.add(role.getRole());//与filterChainDefinitionMap.put("/add", "roles[100002],perms[权限添加]")里的角色值对应起来
    	}
    	redisComponent.setSetValue(key1, roleSet);
    	info.setRoles(roleSet);
    	}
    	if(redisComponent.hasKey(key2)) {
    		Set<Set<String>> set=redisComponent.getSetValue(key2);
    		for (Set<String> set1 : set) {//因为在set方法里做了限制所以只会有一个集合
    			info.setStringPermissions(set1);
			}
    	}
    	else {
    	List<Permission> permissionList = testDao.getPermissionListByUid(userId);//根据用户ID查询所有权限（permission）
    	Set<String> permissionSet = new HashSet<String>();
    	for(Permission Permission : permissionList){
    		permissionSet.add(Permission.getName());//与filterChainDefinitionMap.put("/add", "roles[100002],perms[权限添加]")里的权限值对应起来
    	}
    	redisComponent.setSetValue(key2, permissionSet);
    	info.setStringPermissions(permissionSet);
    	}
           return info;
    }
}
