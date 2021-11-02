package design_pattern.create.singleton.case16;

import design_pattern.create.singleton.case9.IdGenerator;

public class Demo {
    // 1. 老的使用方式
    public void demofunction() {
        //...
        long id = IdGenerator.getInstance().getId();
        //...
    }

    // 2. 新的使用方式：依赖注入
    public void demofunction(IdGenerator idGenerator) {
        long id = idGenerator.getId();
    }
}
