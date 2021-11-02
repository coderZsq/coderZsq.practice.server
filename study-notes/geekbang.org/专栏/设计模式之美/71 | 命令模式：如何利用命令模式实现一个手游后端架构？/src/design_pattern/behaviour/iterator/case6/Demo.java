package design_pattern.behaviour.iterator.case6;

import design_pattern.behaviour.iterator.case1.Iterator;
import design_pattern.behaviour.iterator.case4.ArrayList;
import design_pattern.behaviour.iterator.case4.List;

//代码示例
public class Demo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("a");
        names.add("b");
        names.add("c");
        names.add("d");

        Iterator<String> iterator = names.iterator();
        iterator.next();
        names.remove("a");
        iterator.next();//抛出ConcurrentModificationException异常
    }
}
