import com.lvck1.spring6.iocxml.di2.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDept {

    @Test
    public void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("di-list.xml");
        Dept dept = context.getBean("dept", Dept.class);
        dept.info();
    }
}
