package com.coderZsq.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CacheActivity extends AppCompatActivity {
    private EditText et_name;
    private EditText et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        try {
            showData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showData() throws Exception {
        // 读取保存的数据, 然后设置给控件
        /**
         * 1. 要告诉读哪个文件
         * 2. 告诉要读的文件是否存在
         * 3. 开始读
         * 4. 判断获得的数据是否为空
         * 5. 设置给对应的控件
         */
        // 手写路径是非常不可取的, 应该通过对应的API获得相应的路径
        File file = new File(getCacheDir(), "/info.txt");
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String name = br.readLine();
            String password = br.readLine();
            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)) {
                // 说明用户名和密码都不为空
                et_name.setText(name);
                et_password.setText(password);
            }
        }
    }

    public void login(View view) throws IOException {
        /**
         * 1. 获得输入框的文本 2. 非空判断 3. 保存
         */
        String name = et_name.getText().toString();
        String password = et_password.getText().toString();
        if (TextUtils.isEmpty(name)) {
            et_name.setError("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            et_name.setError("请输入密码");
            return;
        }
        // 走到这里说明用户名和密码整个流程判断完毕
        // 在Android盘符的根目录创建了一个文件叫info.txt
        // File file = new File("data/data/com.coderZsq.ui/info.txt"); 在对应的文件下面写东西
        // 在data/data/com.coderZsq.ui/files/路径下面创建一个文件info.txt, 然后向里面写东西
//        File file = new File(getFilesDir(), "info.txt");
        File file = new File(getCacheDir(), "info.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(name);
        bw.newLine();
        bw.write(password);
        bw.close();

        // 打开文件, 该文件只能由调用该方法的应用程序访问
        // MODE_PRIVATE 该文件只能由调用该方法的应用程序访问
        // MODE_APPEND 如果文件已存在, 就在结尾追加内容, 而不是覆盖文件
        // MODE_WORLD_READABLE 赋予所有应用程序读权限
        // MODE_WORLD_WRITEABLE 赋予所有应用程序写权限
        FileOutputStream outputStream = this.openFileOutput(getFilesDir().getAbsolutePath() + "/info.txt", MODE_WORLD_WRITEABLE + MODE_WORLD_READABLE);
        outputStream.write("hahaha".getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
