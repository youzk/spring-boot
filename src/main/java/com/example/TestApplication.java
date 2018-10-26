package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;



//扫描dao层
@MapperScan("com.example.dao")
@EnableScheduling
@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		System.out.println("The service to start.");
		SpringApplication.run(TestApplication.class, args);
		System.out.println("The service has started.");
	}
}
