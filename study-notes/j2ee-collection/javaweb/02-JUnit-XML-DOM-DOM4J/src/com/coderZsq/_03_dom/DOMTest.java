package com.coderZsq._03_dom;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

// DOM操作测试类
public class DOMTest {
    // 被操作的XML文件
    private File f = new File("/Users/zhushuangquan/Native Drive/GitHub/coderZsq.practice.server.java/study-notes/j2ee-collection/javaweb/02-JUnit-XML-DOM-DOM4J/src/com/coderZsq/_03_dom/contacts.xml");

    // 如何获取Document文档对象
    @Test
    public void testGetDocument() throws Exception {
        // 1): 创建DocumentBuilderFactory对象(意识: 工厂类一般都有一个静态方法用于返回当前工厂类对象)
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 2): 根据工厂对象, 创建DocumentBuilder对象
        DocumentBuilder builder = factory.newDocumentBuilder();
        // 3): 根据builder对象去解析一个已经存在的XML文件, 从而得到Document对象
        Document doc = builder.parse(f);
        System.out.println(doc); // [#document: null]
    }

    /*
     * 需求1: 得到某个具体的文本节点的内容, 取出第二个联系人的名字
     * 操作步骤:
     * 1): 获取Docment文档对象
     * 2): 获取XML中的根元素(contact)
     * 3): 获取第二个联系人元素(linkman)
     * 4): 获取linkman元素下的name子元素
     * 5): 获取name元素的文本内容
     * */
    @Test
    public void test1() throws Exception {
        // 1): 获取Docment文档对象
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        // 2): 获取XML中的根元素(contact)
        Element root = doc.getDocumentElement();
        // 3): 获取第二个联系人元素(linkman)
        Element linkmanEl = (Element) root.getElementsByTagName("linkman").item(1);
        // 4): 获取linkman元素下的name子元素
        Element nameEl = (Element) linkmanEl.getElementsByTagName("name").item(0);
        // 5): 获取name元素的文本内容
        System.out.println(nameEl.getTextContent());
        Assert.assertEquals("Castie!", nameEl.getTextContent());
    }

    /*
     * 需求2: 修改某个元素节点的主体内容, 把第一个联系人的邮箱改掉
     * 操作步骤:
     * 1): 获取Docment文档对象
     * 2): 获取XML中的根元素(contact)
     * 3): 获取第一个联系人元素(linkman)
     * 4): 获取linkman元素下的email子元素
     * 5): 设置email元素的新的文本内容(a13701777868@sina.com)
     * 6): 同步操作: 把内存中的数据同步更新到磁盘的XML中
     * */
    @Test
    public void test2() throws Exception {
        // * 1): 获取Docment文档对象
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        // * 2): 获取XML中的根元素(contact)
        Element root = doc.getDocumentElement();
        // * 3): 获取第一个联系人元素(linkman)
        Element linkmanEl = (Element)root.getElementsByTagName("linkman").item(0);
        // * 4): 获取linkman元素下的email子元素
        Element emailEl = (Element)linkmanEl.getElementsByTagName("email").item(0);
        // * 5): 设置email元素的新的文本内容(a13701777868@sina.com)
        emailEl.setTextContent("a13701777868@sina.com");
        // * 6): 同步操作: 使用核心类Transformer
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer trans = factory.newTransformer();
        Source xmlSource = new DOMSource(doc); // 源: 内存中的Document对象
        Result outputTarget = new StreamResult(f); // 目标: 磁盘中的XML文件(contacts.xml)
        trans.transform(xmlSource, outputTarget); // 同步操作
    }

//     <linkman id="3">
//         <name>Lisa</name>
//         <email>lisa@gmail.com</email>
//         <address>github.com/lisa-microwave</address>
//         <group>FOSUM</group>
//     </linkman>

    /*
     * 需求3: 向指定元素节点中增加子元素节点, 增加一个新的联系人信息
     * 操作步骤:
     * 1): 获取Docment文档对象
     * 2): 获取XML中的根元素(contact)
     * 3): 创建一个linkman元素的片段
     * 3.1): 创建linkman, name, email, address, group元素
     * 3.2): 给name, email, address, group元素设置文本内容
     * 3.3): 把name, email, address, group元素作为linkman元素的子元素
     * 3.4): 把linkman元素作为根元素的子元素
     * 4): 同步操作: 把内存中的数据同步更新到磁盘的XML中
     * */
    @Test
    public void test3() throws Exception {
        // * 1): 获取Docment文档对象
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        // * 2): 获取XML中的根元素(contact)
        Element root = doc.getDocumentElement();
        // =============================================================
        // * 3): 创建一个linkman元素的片段
        // * 3.1): 创建linkman, name, email, address, group元素
        Element linkmanEl = doc.createElement("linkman");
        Element nameEl = doc.createElement("name");
        Element emailEl = doc.createElement("email");
        Element addressEl = doc.createElement("address");
        Element groupEl = doc.createElement("group");
        // * 3.2): 给name, email, address, group元素设置文本内容
        nameEl.setTextContent("Lisa");
        emailEl.setTextContent("lisa@gmail.com");
        addressEl.setTextContent("github.com/lisa-microwave");
        groupEl.setTextContent("FOSUM");
        // * 3.3): 把name, email, address, group元素作为linkman元素的子元素
        linkmanEl.appendChild(nameEl);
        linkmanEl.appendChild(emailEl);
        linkmanEl.appendChild(addressEl);
        linkmanEl.appendChild(groupEl);
        // * 3.4): 把linkman元素作为根元素的子元素
        root.appendChild(linkmanEl);
        // =============================================================
        // * 4): 同步操作: 把内存中的数据同步更新到磁盘的XML中
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer trans = factory.newTransformer();
        Source xmlSource = new DOMSource(doc); // 源: 内存中的Document对象
        Result outputTarget = new StreamResult(f); // 目标: 磁盘中的XML文件(contacts.xml)
        trans.transform(xmlSource, outputTarget); // 同步操作
    }

    // 需求4: 操作XML元素属性: 设置/获取第三个联系人的id属性
    @Test
    public void test4() throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        Element root = doc.getDocumentElement();
        Element linkmanEl = (Element) root.getElementsByTagName("linkman").item(2);
        // =============================================================
        // 获取id属性值
        // String id = linkmanEl.getAttribute("id");
        // 设置id属性
        linkmanEl.setAttribute("id", "123");
        // =============================================================
        // * 4): 同步操作: 把内存中的数据同步更新到磁盘的XML中
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer trans = factory.newTransformer();
        Source xmlSource = new DOMSource(doc); // 源: 内存中的Document对象
        Result outputTarget = new StreamResult(f); // 目标: 磁盘中的XML文件(contacts.xml)
        trans.transform(xmlSource, outputTarget); // 同步操作
    }

    // 需求5: 删除指定元素节点: 删除第三个联系人信息
    @Test
    public void test5() throws Exception {
        Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(f);
        Element root = doc.getDocumentElement();
        Element linkmanEl = (Element) root.getElementsByTagName("linkman").item(2);
        // =============================================================
        // root.removeChild(linkmanEl);
        linkmanEl.getParentNode().removeChild(linkmanEl);
        // =============================================================
        // * 4): 同步操作: 把内存中的数据同步更新到磁盘的XML中
        TransformerFactory factory = TransformerFactory.newInstance();
        Transformer trans = factory.newTransformer();
        Source xmlSource = new DOMSource(doc); // 源: 内存中的Document对象
        Result outputTarget = new StreamResult(f); // 目标: 磁盘中的XML文件(contacts.xml)
        trans.transform(xmlSource, outputTarget); // 同步操作
    }

    // 需求6: 在内存中创建一个Document对象
}
