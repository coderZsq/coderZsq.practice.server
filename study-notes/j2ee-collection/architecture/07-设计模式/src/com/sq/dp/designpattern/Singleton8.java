package com.sq.dp.designpattern;

/**
 * 单例模式 (枚举)
 * 优点:
 *  实现简单, 并且不用考虑线程安全问题, 即使将枚举类序列化保存以后, 再反序列化回来, 还是只存在一个实例
 */
public enum  Singleton8 {
    INSTANCE;

    public String getName() {
        return "枚举";
    }
}
