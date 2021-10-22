package design_principles.ocp.case4;

public class Demo {
    public static void main(String[] args) {
        ApiStatInfo apiStatInfo = new ApiStatInfo();
        // ...省略apiStatInfo的set字段代码
        apiStatInfo.setTimeoutCount(289); // 改动四：设置tiemoutCount值
        ApplicationContext.getInstance().getAlert().check(apiStatInfo);
    }
}