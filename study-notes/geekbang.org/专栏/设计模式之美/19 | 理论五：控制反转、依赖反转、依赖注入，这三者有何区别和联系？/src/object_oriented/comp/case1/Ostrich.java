package object_oriented.comp.case1;

public class Ostrich extends AbstractBird { //鸵鸟
    //...省略其他属性和方法...
    @Override
    public void fly() {
        try {
            throw new UnSupportedMethodException("I can't fly.'");
        } catch (UnSupportedMethodException e) {
            e.printStackTrace();
        }
    }
}
