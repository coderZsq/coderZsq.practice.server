import com.coderZsq.domain.DataSource;
import com.coderZsq.domain.anno.BoyFriend;
import com.coderZsq.domain.xml.GirlFriend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class XmlAppTest {
    @Autowired
    private GirlFriend lucy;

    @Autowired
    private BoyFriend jack;

    @Autowired
    private DataSource dataSource;

    @Test
    public void test() throws Exception {
        System.out.println("lucy = " + lucy);
        System.out.println("jack = " + jack);
    }

    @Test
    public void testDS() throws Exception {
        System.out.println("dataSource = " + dataSource);
    }
}
