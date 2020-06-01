package com.coderZsq.serialization.test;


import com.coderZsq.serialization.domain.User;
import com.coderZsq.serialization.ser.ISerialize;
import com.coderZsq.serialization.ser.impl.JdkSerialize;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class JdkSerializeTest {
    private ISerialize serialize = new JdkSerialize();

    @Test
    public void serialize() throws Exception {
        // 创建一个对象
        User user = new User();
        user.setAge(21);
        user.setName("coderZsq");
        // 设置性别
        // user.setSex("nan");
        byte[] datas = this.serialize.serialize(user);
        System.out.println("数据的长度:" + datas.length);
        // 保存到文件
        try (FileOutputStream fos = new FileOutputStream("user.dat")){
            fos.write(datas);
            fos.flush();
        }
    }

    @Test
    public void deserialize() throws Exception {
        // 反序列化
        byte[] buffer = new byte[1024];
        int len = -1;
        FileInputStream fis = new FileInputStream("user.dat");
        if ((len = fis.read(buffer)) > 0) {
            buffer = Arrays.copyOf(buffer, len);
            User u = serialize.deSerialize(buffer, User.class);
            System.out.println("u = " + u);
            // System.out.println("u.getSex() = " + u.getSex());
        }
    }
}
