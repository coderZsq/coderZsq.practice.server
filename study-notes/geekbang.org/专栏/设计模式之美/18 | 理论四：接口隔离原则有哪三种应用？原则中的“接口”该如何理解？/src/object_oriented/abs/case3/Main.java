package object_oriented.abs.case3;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Logger logger = new FileLogger("access-log", true, Level.WARN, "/users/wangzheng/access.log");
        logger.log(Level.ERROR, "This is a test log message.");
    }
}
