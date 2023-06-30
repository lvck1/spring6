package com.lvck1.spring6.iocxml.di2;

import java.util.List;

public class Dept {
    private String dname;

    private List<Emp> empList;

    public void info() {
        System.out.println("部门名称：" + dname);
        for (Emp emp :
                empList) {
            System.out.println(emp.getEname());
        }
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public void setEmpList(List<Emp> empList) {
        this.empList = empList;
    }
}
