import com.lvck1.spring6.tx.config.SpringConfig;
import com.lvck1.spring6.tx.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class AnnoTest {
    @Test
    public void testAllAnnotation() {
        ApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);
        BookController bookController = context.getBean("bookController", BookController.class);
        bookController.buyBook(1, 1);
    }
}
