package com.coderZsq._01_review;

public class Main {

    public static void main(String[] args) {

//        Mouse mouse = new Mouse();
//        Keyboard keyboard = new Keyboard();
        MotherBoard motherBoard = new MotherBoard();
//        motherBoard.install("mouse", mouse);
//        motherBoard.install("keyboard", keyboard);
//        motherBoard.install("print", new IUSB() {
//            @Override
//            public void swapData() {
//                System.out.println("打印.....");
//            }
//        });
        motherBoard.doWork();
        System.out.println("-----------------------------");
//        motherBoard.uninstall("mouse");
//        motherBoard.doWork();
    }
}
