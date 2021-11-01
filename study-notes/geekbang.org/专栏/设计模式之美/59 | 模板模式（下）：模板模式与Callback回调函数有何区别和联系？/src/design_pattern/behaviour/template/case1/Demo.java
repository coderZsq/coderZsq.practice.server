package design_pattern.behaviour.template.case1;

public class Demo {
    public static void main(String[] args) {
        AbstractClass demo = new ConcreteClass1();
        demo.templateMethod();
    }
}
