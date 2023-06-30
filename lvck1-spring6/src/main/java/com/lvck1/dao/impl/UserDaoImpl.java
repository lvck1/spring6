package com.lvck1.dao.impl;

import com.lvck1.anno.Bean;
import com.lvck1.dao.UserDao;

@Bean
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao....");
    }
}
