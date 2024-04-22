package com.lvck1.spring6.xmltx.controller;

import com.lvck1.spring6.xmltx.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    //买书的方法，传递图书id和用户id
    public void buyBook(Integer bookId, Integer userId) {
        //调用service方法
        bookService.buyBook(bookId, userId);
    }

}
