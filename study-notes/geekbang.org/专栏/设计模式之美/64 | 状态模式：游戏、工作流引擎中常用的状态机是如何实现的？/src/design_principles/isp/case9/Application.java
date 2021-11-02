package design_principles.isp.case9;

public class Application {
    public static final ConfigSource configSource = new ZookeeperConfigSource(/*省略参数*/);
    public static final RedisConfig redisConfig = new RedisConfig(configSource);
    public static final KafkaConfig kafkaConfig = new KafkaConfig(configSource);
    public static final MysqlConfig mySqlConfig = new MysqlConfig(configSource);
    public static final ApiMetrics apiMetrics = new ApiMetrics();
    public static final DbMetrics dbMetrics = new DbMetrics();

    public static void main(String[] args) {
        SimpleHttpServer simpleHttpServer = new SimpleHttpServer("127.0.0.1", 2389);
        simpleHttpServer.addViewers("/config", redisConfig);
        simpleHttpServer.addViewers("/config", mySqlConfig);
        simpleHttpServer.addViewers("/metrics", apiMetrics);
        simpleHttpServer.addViewers("/metrics", dbMetrics);
        simpleHttpServer.run();
    }
}