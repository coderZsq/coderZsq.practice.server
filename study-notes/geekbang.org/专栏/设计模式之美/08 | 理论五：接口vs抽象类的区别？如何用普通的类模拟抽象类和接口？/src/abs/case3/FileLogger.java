package abs.case3;

import java.io.IOException;
import java.io.Writer;

// 子类：输出日志到文件
public class FileLogger extends Logger {
    private Writer fileWriter;

    public FileLogger(String name, boolean enabled,
                      Level minPermittedLevel, String filepath) {
        super(name, enabled, minPermittedLevel);
        //...构造函数不变，代码省略...
    }

    @Override
    public void log(Level level, String mesage) throws IOException {
        if (!isLoggable()) return;
        // 格式化level和message,输出到日志文件
        fileWriter.write(mesage);
    }
}