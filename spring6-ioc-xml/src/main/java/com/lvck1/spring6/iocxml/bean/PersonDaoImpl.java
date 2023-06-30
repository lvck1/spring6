package com.lvck1.spring6.iocxml.bean;

import com.lvck1.spring6.iocxml.User;

public class PersonDaoImpl implements UserDao {
    @Override
    public void run() {
        System.out.println("person run...");
    }
}
