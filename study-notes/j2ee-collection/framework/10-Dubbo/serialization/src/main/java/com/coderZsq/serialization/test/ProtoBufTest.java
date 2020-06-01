package com.coderzsq.serialization.test;

import com.coderzsq.serialization.domain.PersonModel;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Arrays;

public class ProtoBufTest {
    @Test
    public void testSerialize() throws Exception {
        PersonModel.Person person = PersonModel.Person.newBuilder().setName("coderZsq").setAge(21).build();
        // 对对象进行序列化
        byte[] bytes = person.toByteArray();
        // 序列化长度 203 -> 12
        System.out.println("序列化长度" + bytes.length);
        // 保存到文件
        try (FileOutputStream fos = new FileOutputStream("user2.dat")){
            fos.write(bytes);
            fos.flush();
        }
    }

    @Test
    public void testDeSerialize() throws Exception {
        // 反序列化
        byte[] buffer = new byte[1024];
        int len = -1;
        FileInputStream fis = new FileInputStream("user2.dat");
        if ((len = fis.read(buffer)) > 0) {
            buffer = Arrays.copyOf(buffer, len);
            PersonModel.Person person = PersonModel.Person.parseFrom(buffer);
            System.out.println("person = " + person);
        }
    }
}
