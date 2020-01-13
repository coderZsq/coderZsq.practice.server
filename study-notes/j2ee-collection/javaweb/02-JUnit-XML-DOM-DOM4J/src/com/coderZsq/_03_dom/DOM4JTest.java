package com.coderZsq._03_dom;

import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

public class DOM4JTest {
    private File f = new File("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/j2ee-collection/javaweb/02-JUnit-XML-DOM-DOM4J/src/com/coderZsq/_03_dom/contacts.xml");

    // 查询所有的联系人
    @Test
    public void testGetAll() throws Exception {
        // 获取文档对象
        SAXReader reader = new SAXReader();
        org.dom4j.Document doc = reader.read(f);
        // 获取根元素
        Element root = doc.getRootElement();
        // 获取根元素下所有的linkman元素
        List<Element> linkmanElList = root.elements("linkman");
        for (Element linkmanEl : linkmanElList) {
            // 获取每一个linkman元素的name子元素的文本内容
            // Element nameEl = linkmanEl.element("name");
            // String name = nameEl.getText();
            String id = linkmanEl.attributeValue("id");
            String name = linkmanEl.elementText("name");
            String email = linkmanEl.elementText("email");
            String address = linkmanEl.elementText("address");
            String group = linkmanEl.elementText("group");
            System.out.println(id);
            System.out.println(name);
            System.out.println(email);
            System.out.println(address);
            System.out.println(group);
            System.out.println("---------------------");
        }
    }

    // 需求2: 新增一个联系人的信息
    @Test
    public void test2() throws Exception {
        // 获取文档对象
        SAXReader reader = new SAXReader();
        org.dom4j.Document doc = reader.read(f);
        // 获取根元素
        Element root = doc.getRootElement();
        // <linkman id="3">
        //    <name>Lisa</name>
        //    <email>lisa@gmail.com</email>
        //    <address>github.com/lisa-microwave</address>
        //    <group>FOSUM</group>
        // </linkman>
        // 创建linkman元素, 把linkman元素作为根元素的子元素
        Element linkmanEl = root.addElement("linkman").addAttribute("id", "3");
        // 创建name, email, address, group元素, 作为linkman子元素, 设置文本内容
        linkmanEl.addElement("name").setText("Lisa");
        linkmanEl.addElement("email").setText("lisa@gmail.com");
        linkmanEl.addElement("address").setText("github.com/lisa-microwave");
        linkmanEl.addElement("group").setText("FOSUM");
        // 同步操作
        // createCompactFormat: 压缩格式, 输出成一行
        // FileWriter out = new FileWriter(f);
        // doc.write(out);
        // out.close(); // 记得关闭流
        // createPrettyPrint: 良好缩进的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer = new XMLWriter(new FileWriter(f), format);
        writer.write(doc);
        writer.close();
    }

}
