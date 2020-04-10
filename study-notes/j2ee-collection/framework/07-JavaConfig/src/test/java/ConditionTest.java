import com.coderZsq.condition.AppConfig;
import com.coderZsq.condition.GirlFriend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ConditionTest {
    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testCondition() throws Exception {
        // 判断容器中有没有lucy这个实例对象
        System.out.println(applicationContext.getBeansOfType(GirlFriend.class).size());
    }
}
