package com.lvck1.spring6.tx.service;

import com.lvck1.spring6.tx.dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Transactional(noRollbackFor = ArithmeticException.class)
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public void buyBook(Integer bookId, Integer userId) {
        //模拟超时效果
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        //根据图书id查询图书价格
        Integer price = bookDao.getBookPriceByBookId(bookId);
        //更新图书表库存-1
        bookDao.updateStock(bookId);
        //更新用户表用户余额 - 图书价格
        bookDao.updateUserBalance(userId, price);
        System.out.println(1 / 0);
    }
}
