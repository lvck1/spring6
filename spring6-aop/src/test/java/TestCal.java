import com.lvck1.spring6.aop.example.Calculator;
import com.lvck1.spring6.aop.example.CalculatorImpl;
import com.lvck1.spring6.aop.example.ProxyFactory;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCal {
    @Test
    public void test() {
        //创建代理对象(动态)
        ProxyFactory proxyFactory = new ProxyFactory(new CalculatorImpl());
        Calculator proxy = (Calculator) proxyFactory.getProxy();
        proxy.add(1, 2);
        proxy.mul(2, 4);
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        com.lvck1.spring6.aop.annoaop.Calculator calculator = context.getBean(com.lvck1.spring6.aop.annoaop.Calculator.class);
        calculator.add(1, 2);
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beanaop.xml");
        com.lvck1.spring6.aop.xmlaop.Calculator calculator = context.getBean(com.lvck1.spring6.aop.xmlaop.Calculator.class);
        calculator.add(1, 2);
    }
}

