package com.gujun.springbootrabbitmq01.advice;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice(basePackages = {"com.gujun.springbootrabbitmq01.controller.*"},annotations = {RestController.class, Controller.class})
public class MyControllerAdvice {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JSONObject exceptionHandler(Exception ex){
        ex.printStackTrace();
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("result","服务器出错");
        return jsonObject;
    }

}
