package com.gujun.springbootrabbitmq01.service;

import com.gujun.springbootrabbitmq01.dao.EmployeeMapper;
import com.gujun.springbootrabbitmq01.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    @Async  //声明异步调用，这样Spring就会通过之前定义的线程池中线程来执行该方法；
    public void testAsync() throws InterruptedException {
        Thread.sleep(5000);
        System.out.println("执行业务逻辑的线程:"+Thread.currentThread().getName());
    }

    @Override
    public Employee getById(Integer eId) {
        return employeeMapper.getById(eId);
    }
}
