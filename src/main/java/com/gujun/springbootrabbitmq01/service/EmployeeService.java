package com.gujun.springbootrabbitmq01.service;


import com.gujun.springbootrabbitmq01.entity.Employee;

public interface EmployeeService {

    void testAsync() throws InterruptedException;

    Employee getById(Integer eId);

}
