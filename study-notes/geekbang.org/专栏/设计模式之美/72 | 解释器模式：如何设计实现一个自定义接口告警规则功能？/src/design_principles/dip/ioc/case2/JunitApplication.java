package design_principles.dip.ioc.case2;

import java.util.ArrayList;
import java.util.List;

public class JunitApplication {
    private static final List<TestCase> testCases = new ArrayList<>();

    public static void register(TestCase testCase) {
        testCases.add(testCase);
    }

    public static final void main(String[] args) {
        // 注册操作还可以通过配置的方式来实现，不需要程序员显示调用register()
        JunitApplication.register(new UserServiceTest());

        for (TestCase testCase : testCases) {
            testCase.run();
        }
    }
}