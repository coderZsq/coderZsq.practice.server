package com.coderZsq;

public class Main {

    public static void main(String[] args) {
        /*
         * 枚举类型(Enum Type)
         *
         * 如果一个变量的取值只可能是固定的几个值，可以考虑使用枚举类型
         * 枚举由一组预定义的常量构成
         * */
        {
            Season s = Season.WINTER;
            // WINTER
            System.out.println(s.name());
            // 3
            System.out.println(s.ordinal());

            // 冬天
            switch (s) {
                case SPRING:
                    System.out.println("春天");
                    break;
                case SUMMER:
                    System.out.println("夏天");
                    break;
                case FALL:
                    System.out.println("秋天");
                    break;
                case WINTER:
                    System.out.println("冬天");
                    break;
            }
        }

        {
            Season2 s = Season2.SUMMER;
            // 25
            System.out.println(s.getMin());
            // 35
            System.out.println(s.getMax());
        }

        {
            Season3 s = Season3.SUMMER;
            if (s == Season3.SPRING) {
                System.out.println("春天");
            } else if (s == Season3.SUMMER) {
                System.out.println("夏天");
            } else if (s == Season3.FALL) {
                System.out.println("秋天");
            } else if (s == Season3.WINTER) {
                System.out.println("冬天");
            }
        }
    }
}
