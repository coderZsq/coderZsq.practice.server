package design_pattern.struct.proxy.case4;

import design_pattern.struct.proxy.case3.IUserController;
import design_pattern.struct.proxy.case3.UserController;

public class Demo {
    public static void main(String[] args) {
        //MetricsCollectorProxy使用举例
        MetricsCollectorProxy proxy = new MetricsCollectorProxy();
        IUserController userController = (IUserController) proxy.createProxy(new UserController());
    }
}
