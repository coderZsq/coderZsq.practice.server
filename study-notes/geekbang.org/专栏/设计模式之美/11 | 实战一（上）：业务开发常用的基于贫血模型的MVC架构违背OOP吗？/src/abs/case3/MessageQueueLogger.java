package abs.case3;

// 子类: 输出日志到消息中间件(比如kafka)
public class MessageQueueLogger extends Logger {
    private MessageQueueClient msgQueueClient;

    public MessageQueueLogger(String name, boolean enabled,
                              Level minPermittedLevel, MessageQueueClient msgQueueClient) {
        super(name, enabled, minPermittedLevel);
        //...构造函数不变，代码省略...
    }

    @Override
    public void log(Level level, String mesage) {
        if (!isLoggable()) return;
        // 格式化level和message,输出到消息中间件
        msgQueueClient.send(mesage);
    }
}
