package com.coderZsq.ui;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 想在sd卡中写东西
        // 得到sd卡的路径
        String dataDirectoryPath = Environment.getDataDirectory().getAbsolutePath();
        String externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Log.v("data", dataDirectoryPath);
        Log.v("data", "-------------");
        Log.v("data", externalStoragePath);
        try {
            writeData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeData() throws Exception {
        // 1. 先判断sd的状态
        // 2. 如果存在, 先获得一个SD里面的文件
        // 3. 再向里面写 东西
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            // 说明sd卡存在
            // 在SD卡根目录下面创建了一个文件, 这个文件叫 葫芦娃.avi
            File file = new File(Environment.getExternalStorageDirectory(), "葫芦娃.avi");
            FileOutputStream fos = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            for (int i = 0; i < 1000; i++) {
                fos.write(buffer);
            }
            fos.flush();
            fos.close();
        }
    }

}
