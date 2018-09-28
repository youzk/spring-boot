package com.example.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfiguration {
	
	//只要定义过任何的交换机、队列、绑定，都会在rabbitmq中存在，可在管理页面管理
	
	

	//默认用direct类型的交换机，且绑定时的路由键为队列名称
    @Bean
    public Queue Queue() {
        return new Queue("hello");//参数为此队列名称 
        //一个队列，N个接受者,会均匀的将消息发送到N个接收者中
    }
    
  


    @Bean
    public Queue queueMessage() {//方法名与下面绑定时的队列实例名对应起来
        return new Queue("queue1");
    }
    
    @Bean
    public Queue queueMessages() {//方法名与下面绑定时的队列实例名对应起来
        return new Queue("queue2");
    }

    @Bean
    TopicExchange exchange() {//方法名与下面绑定时的交换机实例名对应起来
        return new TopicExchange("TopicExchange");//参数为此Topic类型交换机名称
    }
    
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("FanoutExchange");
    }
    

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {//名称与方法名对应起来
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.message");//交换机绑定队列并指定路由键
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessages, TopicExchange exchange) {//名称与方法名对应起来
        return BindingBuilder.bind(queueMessages).to(exchange).with("topic.#");//交换机绑定队列并指定路由键
    }
    
    
    //FanoutExchange的绑定
    @Bean
    Binding bindingExchangeA(Queue queueMessage,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessage).to(fanoutExchange);//FanoutExchange会把消息发给所有与之绑定的队列，故不需指定路由键
    }

    @Bean
    Binding bindingExchangeB(Queue queueMessages, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueMessages).to(fanoutExchange);
    }
}
