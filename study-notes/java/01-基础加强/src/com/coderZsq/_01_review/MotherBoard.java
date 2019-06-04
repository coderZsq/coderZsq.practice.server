package com.coderZsq._01_review;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class MotherBoard {

    private static Map<String, IUSB> plugins = new HashMap<>();

    static {
        Properties p = new Properties();
        try {
            InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("plugins.properties");
            p.load(inputStream);
            Set<Map.Entry<Object, Object>> entrys = p.entrySet();
            for (Map.Entry<Object, Object> entry: entrys) {
                String className = entry.getValue().toString();
                IUSB usbObject = (IUSB) Class.forName(className).newInstance();
                plugins.put(entry.getKey().toString(), usbObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doWork() {
        for (IUSB usb: plugins.values()) {
            usb.swapData();
        }
    }
}
