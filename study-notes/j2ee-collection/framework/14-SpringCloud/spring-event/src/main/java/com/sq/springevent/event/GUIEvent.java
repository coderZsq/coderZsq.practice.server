package com.sq.springevent.event;

import javax.swing.*;
import java.awt.event.*;

public class GUIEvent {
    public static void main(String[] args) throws Exception {
        // 1 创建一个窗体
        JFrame frame = new JFrame();
        frame.setBounds(200, 300, 500, 500);
        // 绑定事件
        frame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("鼠标点击了窗体...");
                System.out.printf("事件源: %s, 事件信息: %s \n", e.getSource(), e);
            }
        });
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 准备关闭程序
                frame.dispose(); // 触发一个关闭之后的事件
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // 关闭程序
                System.exit(0);
            }
        });
        // 第一个: 事件源: frame 对象
        // 第二个: 事件类型: MouseEvent 事件类型
        // 第三个: 响应函数, 监听程序
        frame.setVisible(true);
        System.in.read();
    }
}
