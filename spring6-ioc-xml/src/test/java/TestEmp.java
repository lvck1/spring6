import com.lvck1.spring6.iocxml.di2.Emp;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestEmp {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di2.xml");
        Emp emp = context.getBean("emp", Emp.class);
        emp.work();
    }

    @Test
    public void test2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di2.xml");
        Emp emp = context.getBean("emp2", Emp.class);
        emp.work();
    }

    @Test
    public void test3() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-di2.xml");
        Emp emp = context.getBean("emp3", Emp.class);
        emp.work();
    }

    @Test
    public void test4() {
        ApplicationContext context = new ClassPathXmlApplicationContext("di-array.xml");
        Emp emp = context.getBean("emp", Emp.class);
        emp.work();
    }
}
