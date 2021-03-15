package com.sq.dp.principle.sp;

/**
 * 单一职责原则:
 * 对象不应承担太多功能, 正如一心不能二用, 比如太多的工作(种类)会使人崩溃. 唯有专注才能保证对象的高内聚; 唯有唯一, 才能保证对象的细粒度.
 */
public class SingleResponsibility1 {
    // ------------------- 客户端 ----------------------
    public static void main(String[] args) {
        // Animal animal = new Animal();
        // animal.breath("猫");
        // animal.breath("狗");
        //
        // // 当前的Animal类不符合单一职责
        // animal.breath("鱼");

        // 将动物分为水生动物和陆生动物, 分别做不同的处理, 符合单一职责原则
        Terrestrial terrestrial = new Terrestrial();
        terrestrial.breath("猫");
        terrestrial.breath("狗");

        Aquatic aquatic = new Aquatic();
        aquatic.breath("鱼");

        // 如果该类足够简单, 一般也会不遵循单一职责原则, 使我们的代码能够更简单的编写
        Animal animal = new Animal();
        animal.breath("猫");
        animal.breath("狗");
        animal.breathInWater("鱼");
    }
}

// ------------------- 服务端 ----------------------
class Animal {
    public void breath(String animal) {
        System.out.println(animal + "呼吸空气");
    }

    public void breathInWater(String animal) {
        System.out.println(animal + "吃水");
    }
}

class Terrestrial {
    public void breath(String animal) {
        System.out.println(animal + "呼吸空气");
    }
}

class Aquatic {
    public void breath(String animal) {
        System.out.println(animal + "吃水");
    }
}