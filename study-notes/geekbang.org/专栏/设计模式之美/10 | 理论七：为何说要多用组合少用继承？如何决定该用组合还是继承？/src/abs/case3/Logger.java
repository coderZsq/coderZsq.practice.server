package abs.case3;

import java.io.IOException;

// 父类：非抽象类，就是普通的类. 删除了log(),doLog()，新增了isLoggable().
public class Logger {
    private String name;
    private boolean enabled;
    private Level minPermittedLevel;

    public Logger(String name, boolean enabled, Level minPermittedLevel) {
        //...构造函数不变，代码省略...
    }

    public void log(Level level, String mesage) throws IOException { // do nothing...
    }

    protected boolean isLoggable() {
        Level level = null;
        boolean loggable = enabled && (minPermittedLevel.intValue() <= level.intValue());
        return loggable;
    }
}

