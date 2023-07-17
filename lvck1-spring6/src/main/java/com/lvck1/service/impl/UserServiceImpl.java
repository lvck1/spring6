package com.lvck1.service.impl;

import com.lvck1.anno.Bean;
import com.lvck1.anno.Di;
import com.lvck1.dao.UserDao;
import com.lvck1.service.UserService;

@Bean
public class UserServiceImpl implements UserService {
    @Di
    private UserDao userDao;


    @Override
    public void add() {
        System.out.println("service......");
        //调用dao方法
        userDao.add();
    }
}
