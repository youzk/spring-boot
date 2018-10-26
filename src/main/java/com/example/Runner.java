package com.example;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * SpringApplication.run() 之前执行，非常适合在应用程序启动之初进行一些数据初始化的工作
 * Runner
 * 创建人:yzk 
 * 时间：2018年10月15日-下午4:02:09 
 * @version 1.0.0
 *
 */
@Component
@Order(1)//@Order 注解的实现类最先执行，并且@Order()里面的值越小启动越早。
public class Runner implements CommandLineRunner{
	
    @Override
    public void run(String... args) throws Exception {
        System.out.println("The Runner start to initialize ...");
    }

}
