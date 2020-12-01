package com.sq;


import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
        // 设置编码
        cfg.setDefaultEncoding("UTF-8");
        // 模板文件的存放目录
        cfg.setDirectoryForTemplateLoading(new File("/Users/zhushuangquan/Codes/GitHub/coderZsq.practice.server/study-notes/j2ee-collection/framework/00-项目驱动/53_Freemaker/src/test/templates"));

        // 获取模板文件
        Template tpl = cfg.getTemplate("mapper.ftl");

        // 数据ata
        Map<String, Object> data = new HashMap<>();
        data.put("type", "Data");

        try (FileWriter out = new FileWriter(new File("/Users/zhushuangquan/Codes/GitHub/coderZsq.practice.server/study-notes/j2ee-collection/framework/00-项目驱动/53_Freemaker/src/test/templates/Data.java"))) {
            tpl.process(data, out);
        }
    }
}
