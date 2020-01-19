package com.coderZsq;

import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@SuppressWarnings("serial")
public class MyFrame extends JFrame {
    public MyFrame() {
        super("简单的计算窗口");
        setBounds(300, 300, 600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        // 字体
        Font font = new Font("微软雅黑", Font.PLAIN, 18);

        JLabel label = new JLabel();
        label.setFont(font);
        add(label);

        // 文本输入框
        JTextField tf = new JTextField(5);
        tf.setFont(font);
        add(tf);

        // 按钮
        JButton btn = new JButton("发送");
        btn.setFont(font);
        btn.addActionListener((evt) -> {
            try (
                    Socket socket = new Socket("127.0.0.1", 8888);
                    OutputStream os = socket.getOutputStream();
                    InputStream is = socket.getInputStream();
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ) {
                // 写数据到服务器
                os.write(tf.getText().getBytes("UTF-8"));
                // 关闭输出(表明写给服务器的内容都写完了)
                socket.shutdownOutput();

                // 读取服务器发送的数据
                byte[] buffer = new byte[8192];
                int len;
                while ((len = is.read(buffer)) != -1) {
                    baos.write(buffer, 0, len);
                }
                byte[] bytes = baos.toByteArray();
                label.setText(new String(bytes, "UTF-8"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        add(btn);
    }
}
