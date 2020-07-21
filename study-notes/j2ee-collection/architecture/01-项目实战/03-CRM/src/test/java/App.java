import ch.qos.logback.classic.Level;
import org.junit.Test;
import org.slf4j.LoggerFactory;

public class App {
    private static final ch.qos.logback.classic.Logger log = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(App.class);
    @Test
    public void test1() throws Exception {
        log.setLevel(Level.ERROR);
        log.trace("trace");
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }

    @Test
    public void test2() throws Exception {
        Long billId = 1L;
        String keyword = "总";
        log.info("billId:[{}], keyword:[{}]", billId, keyword);
    }

    /**
     * 日志输出
     * 第一个: 日志往哪里输出: 控制台, 文件中输出, 网络传输
     * 第二个: 输出的数据的格式 指定一个统一的格式, 到时候对于日志的输出, 就会按照指定的格式进行输出
     * 第三个: 指定输出的日志有效级别: error
     */
}
