package design_pattern.create.singleton.case3;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private FileWriter writer;

    public Logger() {
        File file = new File("/Users/wangzheng/log.txt");
        try {
            writer = new FileWriter(file, true); //true表示追加写入
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void log(String message) {
        synchronized(Logger.class) { // 类级别的锁
            try {
                writer.write(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}