package com.lvck1.spring6.iocxml.di2;

import java.util.Arrays;

public class Emp {
    private String ename;
    private int age;

    private Dept dept;

    private String[] loves;

    public void work() {
        System.out.println(ename + "emp woek..." + age);
        dept.info();
        System.out.println(Arrays.toString(loves));
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public String[] getLoves() {
        return loves;
    }

    public void setLoves(String[] loves) {
        this.loves = loves;
    }
}
