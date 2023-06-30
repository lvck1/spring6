package com.lvck1.spring6.bean.Resource.controller;

import com.lvck1.spring6.bean.Resource.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;

@Controller("myUserController")
public class UserController {

    //根据名称进行注入
    @Resource(name = "myUserService")
    private UserService userService;

    public void add(){
        System.out.println("controller...");
        userService.add();
    }
}
