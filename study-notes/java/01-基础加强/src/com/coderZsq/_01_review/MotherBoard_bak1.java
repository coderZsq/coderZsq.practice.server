package com.coderZsq._01_review;

import java.util.HashMap;
import java.util.Map;

public class MotherBoard_bak1 {

    private Map<String, IUSB> plugins = new HashMap<>();

    public void install(String name, IUSB usb) {
        plugins.put(name, usb);
    }

    public void uninstall(String name) {
        plugins.remove(name);
    }

    public void doWork() {
        for (IUSB usb: plugins.values()) {
            usb.swapData();
        }
    }
}
