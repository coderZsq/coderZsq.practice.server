package design_pattern.behaviour.iterator.case4;

import design_pattern.behaviour.iterator.case1.Iterator;

public class Demo {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("xzg");
        names.add("wang");
        names.add("zheng");

        Iterator<String> iterator = names.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.currentItem());
            iterator.next();
        }
    }
}