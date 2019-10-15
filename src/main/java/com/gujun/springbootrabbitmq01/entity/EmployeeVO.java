package com.gujun.springbootrabbitmq01.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias(value = "emv")
public class EmployeeVO implements Serializable {

    private static final long serialVersionUID = -1102326240197140683L;

    private Integer eId;

    private String eName;

    private int eSex;

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

    public int geteSex() {
        return eSex;
    }

    public void seteSex(int eSex) {
        this.eSex = eSex;
    }

    public EmployeeVO() {
    }

    @Override
    public String toString() {
        return "EmployeeVO{" +
                "eId=" + eId +
                ", eName='" + eName + '\'' +
                ", eSex=" + eSex +
                '}';
    }

}
