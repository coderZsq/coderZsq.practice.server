package design_pattern.create.singleton.case11;

import design_pattern.create.singleton.case9.IdGenerator;

public class Order {
    public void create() {
        //...
        long id = IdGenerator.getInstance().getId();
        // 需要将上面一行代码，替换为下面一行代码
        // long id = UserIdGenerator.getIntance().getId();
    }
}