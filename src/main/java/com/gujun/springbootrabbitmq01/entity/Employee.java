package com.gujun.springbootrabbitmq01.entity;

import com.gujun.springbootrabbitmq01.entity.Enum.SexEnum;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias(value = "em")
public class Employee implements Serializable {

    private static final long serialVersionUID = 3416163895031345777L;

    private Integer eId;

    private String eName;

    private SexEnum eSex;

    public Integer geteId() {
        return eId;
    }

    public void seteId(Integer eId) {
        this.eId = eId;
    }

    public String geteName() {
        return eName;
    }

    public void seteName(String eName) {
        this.eName = eName;
    }

    public SexEnum getSexEnum() {
        return eSex;
    }

    public void setSexEnum(SexEnum eSex) {
        this.eSex = eSex;
    }

    public Employee() {
    }

    @Override
    public String toString() {
        return "Employee{" +
                "eId=" + eId +
                ", eName='" + eName + '\'' +
                ", eSex=" + eSex +
                '}';
    }
}
