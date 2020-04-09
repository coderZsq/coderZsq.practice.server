import com.coderZsq.AppConfig;
import com.coderZsq.domain.*;
import com.coderZsq.domain.anno.BoyFriend;
import com.coderZsq.domain.xml.GirlFriend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class AnnoAppTest {
    @Autowired
    private GirlFriend lucy;

    @Autowired
    private BoyFriend jack;

    @Autowired
    private ApplicationContext context;

    @Autowired // 根据类型导入
    @Qualifier("serviceBean")
    private ServiceBean serviceBean;

    @Resource // 先根据名称导入, 再去根据类型导入
    private ControllerBean controllerBean;

    @Resource
    private DaoBean daoBean;

    @Resource
    private OtherBean otherBean;

    @Resource
    private DataSource dataSource;

    @Test
    public void test() throws Exception {
        System.out.println("lucy = " + lucy);
        System.out.println("jack = " + jack);
        GirlFriend bean = context.getBean(GirlFriend.class);
        System.out.println(bean.getClass()); // 代理对象
        System.out.println(context.getBean(GirlFriend.class));
        System.out.println(context.getBean(GirlFriend.class));
    }

    @Test
    public void testImport() throws Exception {
        System.out.println("serviceBean = " +  serviceBean);
        System.out.println("controllerBean = " + controllerBean);
        System.out.println("daoBean = " + daoBean);
    }

    @Test
    public void testImportResource() throws Exception {
        System.out.println("otherBean = " + otherBean);
    }

    @Test
    public void testDS() throws Exception {
        System.out.println("dataSource = " + dataSource);
    }
    
    @Test
    public void testCondition() throws Exception {
        System.out.println("lucy = " + lucy);
    }
}
