package com.gujun.springbootrabbitmq01.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.gujun.springbootrabbitmq01.entity.Employee;
import com.gujun.springbootrabbitmq01.service.EmployeeService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SendMessageController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getById/{eId}")
    public JSONObject sendMsg(@PathVariable("eId") Integer eId){
        JSONObject jsonObject=new JSONObject();
        Employee employee=employeeService.getById(eId);
        jsonObject.put("result","success");
        jsonObject.put("employee",employee.toString());
        //将携带了路由键值testDirectRouting的消息发送到交换机testDirectExchange；✳
        rabbitTemplate.convertAndSend("testDirectExchange","testDirectRouting",JSONObject.parseObject(
                jsonObject.toJSONString(),new TypeReference<Map<String, String>>(){}    //JSONObject->Map
        ));
        return jsonObject;
    }

    @GetMapping("/sendTopicMan")
    public JSONObject sendTopicMan(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("msg","send to topicExchange,to manQueue");
        jsonObject.put("result","success");
        rabbitTemplate.convertAndSend("testTopicExchange","topic.man",jsonObject);
        return jsonObject;
    }

    @GetMapping("/sendTopicWoman")
    public JSONObject sendTopicWoman(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("msg","send to topicExchange,to womanQueue");
        jsonObject.put("result","success");
        rabbitTemplate.convertAndSend("testTopicExchange","topic.woman",jsonObject);
        return jsonObject;
    }

    @GetMapping("/sendFanoutMsg")
    public JSONObject sendFanoutMsg(){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("msg","send to fanoutExchange");
        jsonObject.put("result","success");
        rabbitTemplate.convertAndSend("textFanoutExchange",null,jsonObject);    //无需路由键
        return jsonObject;
    }

}
