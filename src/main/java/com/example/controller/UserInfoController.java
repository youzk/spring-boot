package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.common.Result;
import com.example.domain.User;
import com.example.service.TestService;

//返回页面时用这个注解
@Controller
@RequestMapping("/userInfo")
public class UserInfoController {

	@Autowired
	private TestService testService;

    /**
     * 用户添加;
     * @return
     */
    @RequestMapping(value="/userAdd", method = RequestMethod.POST)
    public String userAdd(User user){
    	Result<String> result=testService.userAdd(user);
        return "userInfoAdd111";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping(value="/userDel", method = RequestMethod.GET)
    public String userDel(){
        return "userInfoDel";
    }
}
