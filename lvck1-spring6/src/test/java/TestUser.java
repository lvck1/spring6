import com.lvck1.bean.AnnotationApplicationContext;
import com.lvck1.bean.ApplicationContext;
import com.lvck1.service.UserService;
import org.junit.jupiter.api.Test;

public class TestUser {
    @Test
    public void test1() {
        ApplicationContext context = new AnnotationApplicationContext("com.lvck1");
        UserService userService = (UserService) context.getBean(UserService.class);
        System.out.println(userService);
        userService.add();
    }
}
