package com.example.controller;




import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.common.Result;

//返回页面时用这个注解
@Controller
public class HomeController {
	
	
	
    @RequestMapping(value ={"/","/index"}, method = RequestMethod.GET)
    public String index(){
        return"index";
    }

    /**
     * 登录展示
     * com.example.controller 
     * 方法名：login
     * 创建人：yzk 
     * 时间：2018年9月20日-上午9:17:19 
     * @return String
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    
    /**
     * 登录处理
     * com.example.controller 
     * 方法名：submitLogin
     * 创建人：yzk 
     * 时间：2018年9月20日-上午9:18:22 
     * @param username
     * @param password
     * @return Result<String>
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value="/ajaxLogin",method=RequestMethod.POST)
    @ResponseBody
    public Result<String> submitLogin(String username, String password,boolean status) {
    	Result<String> result=new Result<>();
    	try {
			UsernamePasswordToken token = new UsernamePasswordToken(username,password,status);//第三个参数设置是否使用remember me功能
    		SecurityUtils.getSubject().login(token);
    	} catch (Exception e) {
    		System.err.println(e.getMessage());
    		result.setStatus(false);
    		result.setResult(500);
    		result.setDesc(e.getMessage());
    	}
    	
       return result;
    }

    
    @RequestMapping(value="/logout",method =RequestMethod.GET)
    @ResponseBody
    public void logout(){
    	try {
    		//退出
    		SecurityUtils.getSubject().logout();//退出后会自动跳转到 "/login"
    	} catch (Exception e) {
    		System.err.println(e.getMessage());
    	}
    }
    
    
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
