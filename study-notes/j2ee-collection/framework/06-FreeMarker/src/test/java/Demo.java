import freemarker.template.Configuration;
import org.junit.Test;

import java.io.File;

public class Demo {
    @Test
    public void test1() throws Exception {
        // 程序=模板+数据
        String info = "你好, %s";
        System.out.println(String.format(info, "老王"));
        System.out.println(String.format(info, "老李"));
        System.out.printf(info, "小王");
    }

    /**
     * 模板应用测试
     * @throws Exception
     */
    @Test
    public void testTemplate() throws Exception {
        // 创建一个配置对象
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_30);
        // 告诉配置对象的模板所在的目录
        cfg.setDirectoryForTemplateLoading(new File());
    }
}
