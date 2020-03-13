import com.coderZsq.crm.domain.Department;
import com.coderZsq.crm.service.IDepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class DepartmentTest {

    @Autowired
    private IDepartmentService departmentService;

    @Test
    public void testQuery() throws Exception {
        List<Department> departments = departmentService.queryAll();
        System.out.println(departments);
    }

    @Test
    public void testSave() throws Exception {
        Department department = new Department();
        department.setName("test009");
        department.setSn("909");
        departmentService.insert(department);
    }
}
