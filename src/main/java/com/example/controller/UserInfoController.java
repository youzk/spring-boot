package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
        return "userInfoAdd";
    }

    /**
     * 用户删除;
     * @return
     */
    @RequestMapping(value="/userDel", method = RequestMethod.GET)
    public String userDel(){
        return "userInfoDel";
    }
    
    /**
     * 上传文件页面
     * @return
     */
    @RequestMapping(value="/uploadPage", method = RequestMethod.GET)
    public String uploadPage(){
        return "upload";
    }
    
    
    /**
     * 上传文件处理
     * @return
     */
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody//此时是返回json所以要加上@ResponseBody注解
    public Result<String> upload(@RequestParam("file") MultipartFile file){
        return testService.upload(file);
    }
}
