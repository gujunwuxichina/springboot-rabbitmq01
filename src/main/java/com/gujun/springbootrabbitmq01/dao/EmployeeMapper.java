package com.gujun.springbootrabbitmq01.dao;

import com.gujun.springbootrabbitmq01.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeMapper {

    Employee getById(Integer eId);

}
