package com.example.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.common.MailComponent;

@Component
@RabbitListener(queues = "queue1")//接收者从此名称的队列取消息
public class HelloReceiver {

	@Autowired
	private MailComponent mailComponent;
	
    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver1 : " + hello);
        String rscId = "77";
        String content="<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        String imgPath = "C:\\Users\\yzk\\Desktop\\微信图片20180.png";
        mailComponent.sendInlineResourceMail("youzk@bransa.cn", "主题：这是有图片的邮件", content, imgPath, rscId);
    }

}
