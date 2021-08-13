package org.geektimes.projects.user.management;

import com.sun.jmx.mbeanserver.Introspector;
import org.geektimes.projects.user.domain.User;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class UserMBeanDemo {

    public static void main(String[] args) throws Exception {
        // 获取平台 MBean Server
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
        // 为 UserXMBean 定义 ObjectName
        ObjectName objectName = new ObjectName(" org.geektimes.projects.user.management:type=User");
        // 创建 UserManagerMBean 实例
        User user = new User();
        mBeanServer.registerMBean(createUserMBean(user), objectName);
        while (true) {
            Thread.sleep(2000);
            System.out.println(user);
        }
    }

    private static Object createUserMBean(User user) throws NotCompliantMBeanException {
        return new UserManager(user);
    }
}
