package abs.case2;

// 父类：非抽象类，就是普通的类. 删除了log(),doLog()，新增了isLoggable().
public class Logger {
    private String name;
    private boolean enabled;
    private Level minPermittedLevel;

    public Logger(String name, boolean enabled, Level minPermittedLevel) {
        //...构造函数不变，代码省略...
    }

    protected boolean isLoggable() {
        Level level = null;
        boolean loggable = enabled && (minPermittedLevel.intValue() <= level.intValue());
        return loggable;
    }
}

