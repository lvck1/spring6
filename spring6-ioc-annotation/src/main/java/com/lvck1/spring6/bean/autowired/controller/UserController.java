package com.lvck1.spring6.bean.autowired.controller;

import com.lvck1.spring6.bean.autowired.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {
    //注入service
    //第一种方式，属性注入
//    @Autowired//根据类型找到对应的对象，完成注入
//    private UserService userService;

    //第二种方式，set方法注入
//    private UserService userService;

//    @Autowired
//    public void setUserService(UserService userService) {
//        this.userService = userService;
//    }

    //第三种方式，构造方法注入
//    private UserService userService;

//    @Autowired
//    UserController(UserService userService){
//        this.userService = userService;
//    }

    //第四种方式，形参注入
//    private UserService userService;

//    UserController(@Autowired UserService userService){
//        this.userService = userService;
//    }

    //第五种方式，只有一个有参构造函数，无注解
    private UserService userService;

    UserController(UserService userService){
        this.userService = userService;
    }

    public void add(){
        System.out.println("controller...");
        userService.add();
    }
}
