package com.lvck1.spring6.iocxml.auto.controller;

import com.lvck1.spring6.iocxml.auto.service.UserService;
import com.lvck1.spring6.iocxml.auto.service.UserServiceImpl;

public class UserController {

    private UserService userService;
    public void addUser(){
        System.out.println("controller方法执行了。。。");
        userService.addUserService();
//        UserService userService = new UserServiceImpl();
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
