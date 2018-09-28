package com.example.controller;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.common.MailComponent;
import com.example.common.RedisComponent;
import com.example.rabbitmq.HelloSender;
import com.example.service.TestService;

@RestController
@RequestMapping("/yzk")
public class HelloWorldController {
	
	@Autowired
	private RedisComponent redisComponent;
	
	@Autowired
	private HelloSender helloSender;
	
	@Autowired
	private MailComponent mailComponent;
	
	@Autowired
	private TestService testService;
	
	
	
	@Value("${fuck}")
	private String title;
	
	
	
	
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String index() {
        return "Hello World";
    }
    
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return title;
    }
    
    /**
     * 测试redis
     * com.example.controller 
     * 方法名：testRedis
     * 创建人：yzk 
     * 时间：2018年8月22日-上午10:29:30 
     * @return Object
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/testRedis", method = RequestMethod.GET)
    public void testRedis() {
    	Set<String> set=new LinkedHashSet<>();
    	set.add("aa");
    	set.add("bb");
    	set.add("gg1111");
    	redisComponent.setSetValue("lpl:lpl2", set);//每当set集合里面的元素发生变化，就是存进一个新的set集合进去
    	Set<Set<String>> set1=redisComponent.getSetValue("lpl:lpl2");
    	for ( Set<String> set2 : set1) {
    		for (String string : set2) {
    			System.out.println(string);
			}
			
		}
    }
    
    
    /**
     * 共享session
     * com.example.controller 
     * 方法名：testRedisSession
     * 创建人：yzk 
     * 时间：2018年8月29日-下午2:46:18 
     * @param session
     * @return String
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/testRedisSession", method = RequestMethod.GET)
    public String testRedisSession(HttpSession session) {
    	session.setAttribute("edg", "gggg");  
        return session.getId();
    }
    
    
    /**
     * 测试Mybatis多源数据库
     * com.example.controller 
     * 方法名：testMybatis
     * 创建人：yzk 
     * 时间：2018年9月4日-上午10:35:09 
     * @return String
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/testMybatis", method = RequestMethod.GET)
    public String testMybatis() {
        return testService.testMybatis();
    }
    
    
    
    /**
     * 测试RabbitMq
     * com.example.controller 
     * 方法名：testRabbitMq
     * 创建人：yzk 
     * 时间：2018年9月4日-下午3:11:17 
     * @throws Exception void
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/testRabbitMq", method = RequestMethod.GET)
    public void  testRabbitMq() throws Exception{
/*    	for (int i=0;i<100;i++){
    		helloSender.send(i);
    	}*/
    	
    	helloSender.sendFanout();
    }
    
    
    /**
     * 测试mail
     * com.example.controller 
     * 方法名：testMail
     * 创建人：yzk 
     * 时间：2018年9月6日-下午3:09:21 
     * @throws Exception void
     * @exception 
     * @since  1.0.0
     */
    @RequestMapping(value = "/testMail", method = RequestMethod.GET)
    public void  testMail() throws Exception{
    	/*mailComponent.sendSimpleMail("youzk@bransa.cn","test simple mail"," hello this is simple mail！！");*/
    	
/*        String content="<html>\n" +
                "<body>\n" +
                "    <font size=\"6\" color=\"red\">hello world ! 这是一封Html邮件!</font> \n" +
                "</body>\n" +
                "</html>";
        mailComponent.sendHtmlMail("youzk@bransa.cn","test html mail",content);*/
    	
/*    	String filePath="C:\\Users\\yzk\\Desktop\\新建文本文档.txt";
    	mailComponent.sendAttachmentsMail("youzk@bransa.cn", "主题：带附件的邮件", "有附件，请查收！", filePath);*/
    	
        String rscId = "77";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\yzk\\Desktop\\微信图片20180.png";

        mailComponent.sendInlineResourceMail("youzk@bransa.cn", "主题：这是有图片的邮件", content, imgPath, rscId);
    	
    }
}
