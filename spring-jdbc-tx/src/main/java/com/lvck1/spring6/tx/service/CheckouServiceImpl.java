package com.lvck1.spring6.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckouServiceImpl implements CheckoutService {
    //注入bookService
    @Autowired
    private BookService bookService;

    //买多本书的方法
    @Transactional
    @Override
    public void checkout(Integer[] bookIds, Integer userId) {
        for (Integer bookId : bookIds) {
            //调用bookService里的方法进行买书
            bookService.buyBook(bookId, userId);
        }
    }
}
