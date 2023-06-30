import com.lvck1.spring6.iocxml.scope.Orders;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestOrders {

    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-scope.xml");
        Orders orders = context.getBean("orders",Orders.class);
        System.out.println(orders);
        Orders orders1 = context.getBean("orders",Orders.class);
        System.out.println(orders1);
    }
}
