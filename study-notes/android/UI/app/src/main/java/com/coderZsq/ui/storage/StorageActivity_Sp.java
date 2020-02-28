package com.coderZsq.ui.storage;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.coderZsq.ui.R;

import java.io.IOException;

public class StorageActivity_Sp extends Activity {
    private EditText et_name;
    private EditText et_password;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        et_name = findViewById(R.id.et_name);
        et_password = findViewById(R.id.et_password);
        showData();
    }

    private void showData() {
        // 先获得保存的数据
        // 将获得数据做非空判断
        // 将数据设置给控件
        String name = SpUtils.getString(this, Constantin.NAME);
        String password = SpUtils.getString(this, Constantin.PASSWORD);
        et_name.setText(name);
        et_password.setText(password);
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
        SpUtils.SafeString(this, Constantin.NAME, name);
        SpUtils.SafeString(this, Constantin.PASSWORD, password);

    }
}
