package com.coderZsq._31_message.dao.impl;

import com.coderZsq._31_message.dao.IMessageDAO;
import com.coderZsq._31_message.domain.Message;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageDAOImpl implements IMessageDAO {
    File file = new File("/Users/zhushuangquan/Desktop/msgs.xml");

    @Override
    public void add(Message obj) {
        Document doc = this.getDoc();
        Element root = doc.getDocumentElement();

        Element msgEl = doc.createElement("msg");
        msgEl.setAttribute("id", obj.getSn());
        Element titleEl = doc.createElement("title");
        titleEl.setTextContent(obj.getTitle());
        Element contentEl = doc.createElement("content");
        contentEl.setTextContent(obj.getContent());
        msgEl.appendChild(titleEl);
        msgEl.appendChild(contentEl);
        root.appendChild(msgEl);
        this.transform2File(doc);
    }

    private void transform2File(Document doc) throws TransformerFactoryConfigurationError {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer former = factory.newTransformer();
            Source xmlSource = new DOMSource(doc);
            Result outputTarget = new StreamResult(file);
            former.transform(xmlSource, outputTarget);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Message> getAll() {
        List<Message> ret = new ArrayList<>();
        Document doc = getDoc();
        Element rootEl = doc.getDocumentElement();
        NodeList nodeList = rootEl.getElementsByTagName("msg");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element msgEl = (Element)nodeList.item(i);
            Message p = new Message();
            p.setSn(msgEl.getAttribute("id"));
            Element titleEl = (Element) msgEl.getElementsByTagName("title").item(0);
            String title = titleEl.getTextContent();
            p.setTitle(title);
            p.setContent(msgEl.getElementsByTagName("content").item(0).getTextContent());
            ret.add(p);
        }
        return ret;
    }

    private Document getDoc() {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            doc = builder.parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return doc;
    }
}
