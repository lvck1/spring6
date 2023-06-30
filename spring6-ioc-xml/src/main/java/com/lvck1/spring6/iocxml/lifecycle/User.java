package com.lvck1.spring6.iocxml.lifecycle;

public class User {
    private String name;

    User() {
        System.out.println("1.bean对象创建，调用无参数构造");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("2.给bean对象设置属性值");
        this.name = name;
    }

    //初始化方法
    public void initMethod() {
        System.out.println("4.bean对象初始化，调用指定的方法进行初始化");
    }

    public void destroyMethod() {
        System.out.println("7.bean对象销毁，调用指定的销毁的方法");
    }
}
