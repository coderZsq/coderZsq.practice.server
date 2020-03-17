package com.coderZsq.base.xmlparser;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;

import com.coderZsq.base.R;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

public class XMLActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            // 1. 创建一个Pull解析器
            XmlPullParser pullParser = Xml.newPullParser();
            // 2. 将文件读进来
            InputStream inputStream = getResources().getAssets().open("weather.xml");
            // 3. 开始解析文档
            pullParser.setInput(inputStream, "utf-8");
            // 3.1 判断如果没有去取文档完毕 就循环遍历
            int eventType = pullParser.getEventType();
            String text = null;
            while (eventType != XmlPullParser.END_DOCUMENT) {
                // 获得标签不是结束标签
                // 3.2 拿到开标签
                // 3.3 拿到开标签后的文本 pullParser.nextText
                if (eventType == XmlPullParser.START_TAG && pullParser.getName().equals("string")) {
                    text += pullParser.nextText() + "\n";
                }
                // 3.4 移动游标
                eventType = pullParser.next();
            }
            Log.v("c", text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
