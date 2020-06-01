package com.coderZsq.serialization.domain;

import com.coderZsq.serialization.ser.ISerialize;
import com.coderZsq.serialization.ser.impl.JdkSerialize;
import lombok.Data;

import java.io.Serializable;

@Data
public class User /*extends Person*/ implements Cloneable, Serializable {
    private static final long serialVersionUID = -1452918099095353464L;
    // transient 修饰的字段不会被序列化
    // private transient Integer age;
    private Integer age;
    private String name;
    // private GirlFriend gf; // 在拷贝的时候, 并没有创建一个新的对象, 拷贝的只是一个内存地址值

    @Override
    public User clone() throws CloneNotSupportedException {
        return (User) super.clone(); // 浅克隆, 只会拷贝内存地址值, 对引用类型, 还是同一个对象
    }

    public User deepClone() {
        // 把对象序列化为字节数组
        ISerialize serialize = new JdkSerialize();
        byte[] bytes = serialize.serialize(this);
        return serialize.deSerialize(bytes, User.class);
    }
}
