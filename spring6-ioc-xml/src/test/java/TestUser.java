import com.lvck1.spring6.iocxml.User;
import com.lvck1.spring6.iocxml.bean.UserDao;
import com.lvck1.spring6.iocxml.di.Book;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUser {

    Logger logger = LoggerFactory.getLogger(TestUser.class);

    @Test
    public void testUser() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        User user = (User) context.getBean("user");
        logger.info("根据id获取Bean:" + user);

        User user2 = context.getBean(User.class);
        logger.info("根据类型获取Bean:" + user2);

        User user3 = context.getBean("user", User.class);
        logger.info("根据id和类型获取Bean:" + user3);
    }

    @Test
    public void testUserDao() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserDao userDao = context.getBean("personDaoImpl", UserDao.class);
        userDao.run();
    }

    @Test
    public void testSetter() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");
        Book book = context.getBean("book", Book.class);
        logger.info(book.toString());
    }

    @Test
    public void testConstructor() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di.xml");
        Book book = context.getBean("bookCon", Book.class);
        logger.info(book.toString());
    }
}
