import com.lvck1.spring6.bean.autowired.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestUserController {
    @Test
    public void test1(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        UserController controller = context.getBean(UserController.class);
        controller.add();
    }

    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        com.lvck1.spring6.bean.Resource.controller.UserController  controller = context.getBean(com.lvck1.spring6.bean.Resource.controller.UserController .class);
        controller.add();
    }
}
