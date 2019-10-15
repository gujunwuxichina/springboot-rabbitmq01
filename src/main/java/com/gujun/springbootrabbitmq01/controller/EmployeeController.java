package com.gujun.springbootrabbitmq01.controller;

import com.alibaba.fastjson.JSONObject;
import com.gujun.springbootrabbitmq01.entity.Employee;
import com.gujun.springbootrabbitmq01.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/getById/{eId}")
    public JSONObject getById(@PathVariable("eId") Integer eId){
        JSONObject jsonObject=new JSONObject();
        Employee employee=employeeService.getById(eId);
        jsonObject.put("result","success");
        jsonObject.put("employee",employee);
        return jsonObject;
    }

}
