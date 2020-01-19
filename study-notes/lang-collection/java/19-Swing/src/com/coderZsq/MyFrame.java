package com.coderZsq;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
    public MyFrame() {
        super("简单的计算窗口");
        setBounds(300, 300, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        // 字体
        Font font = new Font("微软雅黑", Font.PLAIN, 18);

        // 文本输入框
        JTextField tf1 = new JTextField(5);
        tf1.setFont(font);
        add(tf1);

        // 文本标签
        JLabel addLabel = new JLabel("+");
        addLabel.setFont(font);
        add(addLabel);

        // 文本输入框
        JTextField tf2 = new JTextField(5);
        tf2.setFont(font);
        add(tf2);

        // 文本标签
        JLabel equalLabel = new JLabel("=");
        equalLabel.setFont(font);
        add(equalLabel);

        // 文本标签
        JLabel resultLabel = new JLabel("?");
        resultLabel.setFont(font);
        add(resultLabel);

        // 按钮
        JButton btn = new JButton("计算");
        btn.setFont(font);
        btn.addActionListener((evt) -> {
            try {
                int n1 = Integer.parseInt(tf1.getText());
                int n2 = Integer.parseInt(tf2.getText());
                resultLabel.setText(n1 + n2 + "");
            } catch (Exception e) {
                int w = 300;
                int h = 100;
                int x = getX() + ((getWidth() - w) >> 1);
                int y = getY() + ((getHeight() - h) >> 1);
                JDialog dialog = new JDialog(this, true);
                dialog.setTitle("警告");
                dialog.setBounds(x, y, w, h);
                dialog.setResizable(false);

                JLabel label = new JLabel("   请输入正确格式的整数!!!");
                label.setFont(font);
                dialog.add(label);

                dialog.setVisible(true);
            }
        });
        add(btn);

        JTextField tf3 = new JTextField(10);
        tf3.setFont(font);
        add(tf3);

        JTextField tf4 = new JTextField(10);
        tf4.setFont(font);
        add(tf4);

        JButton saveBtn = new JButton("保存");
        saveBtn.setFont(font);
        saveBtn.addActionListener((evt) -> {
            Files.write(tf3.getText().getBytes(), new File(tf4.getText()));
        });
        add(saveBtn);
    }
}
