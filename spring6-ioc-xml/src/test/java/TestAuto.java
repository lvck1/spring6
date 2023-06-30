import com.lvck1.spring6.iocxml.auto.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAuto {

    @Test
    public void test(){
        ApplicationContext context  = new ClassPathXmlApplicationContext("bean-auto.xml");
        UserController userController = context.getBean("userController",UserController.class);
        userController.addUser();
    }
}
