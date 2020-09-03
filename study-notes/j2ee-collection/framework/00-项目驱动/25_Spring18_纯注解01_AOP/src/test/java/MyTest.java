import com.sq.cfg.AppConfig;
import com.sq.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void test() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService service = ctx.getBean("userService", UserService.class);
        service.list();
    }
}
