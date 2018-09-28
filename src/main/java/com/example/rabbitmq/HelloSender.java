package com.example.rabbitmq;



import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * rabbitmq的发送者
 * HelloSender
 * 创建人:yzk 
 * 时间：2018年9月5日-上午10:10:34 
 * @version 1.0.0
 *
 */
@Component
public class HelloSender {

	
	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(int i) {
		String context = "spirng boot neo queue"+" ****** "+i;
/*      User user=new User();
		user.setUserName("yzk");
		user.setPassWord("qqq");
		完美的支持对象的发送和接收，不需要格外的配置*/
		this.rabbitTemplate.convertAndSend("hello", context);
	}
	
	
	public void send1() {
		String context = "hi, i am message 1";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("TopicExchange", "topic.message", context);//三个参数分别为交换机，路由键，消息
	}

	public void send2() {
		String context = "hi, i am messages 2";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("TopicExchange", "topic.messages", context);
	}
	
	public void sendFanout() {
		String context = "hi, i am messages fanout";
		System.out.println("Sender : " + context);
		this.rabbitTemplate.convertAndSend("FanoutExchange", "", context);
	}
}
