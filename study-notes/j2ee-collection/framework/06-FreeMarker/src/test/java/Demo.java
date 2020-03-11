import freemarker.template.Configuration;
import freemarker.template.Template;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.util.HashMap;

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
        cfg.setDirectoryForTemplateLoading(new File("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/j2ee-collection/framework/06-FreeMarker/src/main/resources"));
        // 准备数据
        HashMap<String, String> root = new HashMap<>();
        root.put("user", "老李");
        root.put("url", "http://www.taobao.com");
        root.put("name", "网红口红");
        // 获取到实际的模板对象
        Template template = cfg.getTemplate("hello.ftl");
        // 把数据和模板结合, 解析
        // 控制台输出的字符流
        // OutputStreamWriter out = new OutputStreamWriter((System.out));
        FileWriter fileWriter = new FileWriter(new File(("/Users/zhushuangquan/Desktop/index.html")));
        // 字符流(缓存效果) 和字节流
        template.process(root, fileWriter);
    }
}
