package com.gujun.springbootrabbitmq01.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter(RabbitAutoConfiguration.class)
public class RabbitMQConfig {

    /*
        推送消息的四种情况：
        1.消息推送到server，但是在server里找不到交换机
        2.消息推送到server，找到交换机了，但是没找到队列
        3.消息推送到sever，交换机和队列啥都没找到
        4.消息推送成功
        回调函数作用,可以在回调函数根据需求做对应的扩展或者业务数据处理;
     */
    @Bean
    public RabbitTemplate rabbitTemplate(@Autowired ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate=new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        //设置开启Mandatory,才能触发回调函数，无论消息推送怎么样都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean b, String cause) {
                System.out.println("ConfirmCallback:"+"相关数据："+correlationData);
                System.out.println("ConfirmCallback:"+"确认情况："+b);
                System.out.println("ConfirmCallback:"+"原因："+cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback:"+"消息："+message);
                System.out.println("ReturnCallback:"+"回应码："+replyCode);
                System.out.println("ReturnCallback:"+"回应信息："+replyText);
                System.out.println("ReturnCallback:"+"交换机："+exchange);
                System.out.println("ReturnCallback:"+"路由键："+routingKey);
            }
        });
        return rabbitTemplate;
    }

}
