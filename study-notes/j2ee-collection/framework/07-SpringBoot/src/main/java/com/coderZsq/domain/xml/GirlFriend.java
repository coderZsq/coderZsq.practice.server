package com.coderZsq.domain.xml;

import com.coderZsq.domain.Nanny;
import lombok.Data;

@Data
public class GirlFriend {
    private int age; // 基本属性

    private Nanny nanny; // 引用对象

    public void init() {
        System.out.println("GirlFriend.init");
    }

    public void destroy() {
        System.out.println("GirlFriend.destroy");
    }
}
