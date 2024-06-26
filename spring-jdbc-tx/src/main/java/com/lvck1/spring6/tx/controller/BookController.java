package com.lvck1.spring6.tx.controller;

import com.lvck1.spring6.tx.service.BookService;
import com.lvck1.spring6.tx.service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CheckoutService checkoutService;

    //买书的方法，传递图书id和用户id
    public void buyBook(Integer bookId, Integer userId) {
        //调用service方法
        bookService.buyBook(bookId, userId);
    }

    //买多本书的方法
    public void checkout(Integer[] bookIds, Integer userId) {
        checkoutService.checkout(bookIds, userId);
    }
}
