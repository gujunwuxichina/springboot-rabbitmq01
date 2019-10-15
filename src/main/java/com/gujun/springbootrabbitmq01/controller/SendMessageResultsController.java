package com.gujun.springbootrabbitmq01.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//用来演示推送信息结果的四种情况
@RestController
public class SendMessageResultsController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //消息推送到server，但是在server里找不到交换机
    //触发ConfirmCallback，result:false
    @GetMapping("/noExchange")
    public void noExchange(){
        rabbitTemplate.convertAndSend("not exist exchange", "TestDirectRouting","isExist");
    }

    //消息推送到server，找到了交换机，但没找到队列（此时需要新增一个交换机，但不给其绑定队列）
    //触发ConfirmCallback(result:true,找到了交换机)和ReturnCallback；
    @GetMapping("/exchangeNoQueue")
    public void exchangeNoQueue(){
        rabbitTemplate.convertAndSend("noQueueDirectExchange", "TestDirectRouting","hasQueue");
    }

    //消息推送到server,交换机和队列都没有找到
    //触发ConfirmCallback，result:false;
    @GetMapping("/noExchangeAndQueue")
    public void noExchangeAndQueue(){
        rabbitTemplate.convertAndSend("not exist exchange", "TestDirectRouting","isExist");
    }

    //消息推送正常时，触发ConfirmCallback，result:true;

}
