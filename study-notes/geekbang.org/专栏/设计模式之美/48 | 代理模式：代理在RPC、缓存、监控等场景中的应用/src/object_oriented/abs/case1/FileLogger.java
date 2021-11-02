package object_oriented.abs.case1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

// 抽象类的子类：输出日志到文件
public class FileLogger extends Logger {
    private Writer fileWriter;

    public FileLogger(String name, boolean enabled,
                      Level minPermittedLevel, String filepath) throws IOException {
        super(name, enabled, minPermittedLevel);
        this.fileWriter = new FileWriter(filepath);
    }

    @Override
    public void doLog(Level level, String mesage) throws IOException {
        // 格式化level和message,输出到日志文件
        fileWriter.write(mesage);
    }
}
