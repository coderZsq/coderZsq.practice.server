package design_pattern.create.builder.case2;

public class Demo {
    public static void main(String[] args) {
        // ResourcePoolConfig使用举例
        ResourcePoolConfig config = new ResourcePoolConfig("dbconnectionpool");
        config.setMaxTotal(16);
        config.setMaxIdle(8);
    }
}
