package com.coderZsq.se;

import cn.hutool.core.util.IdUtil;

public class App {
    public static void main(String[] args) {
        System.out.println("客户管理系统");
        System.out.println("生成唯一的id: " + IdUtil.fastSimpleUUID());
    }
}
