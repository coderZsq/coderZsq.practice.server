package com.coderZsq._01_review;

public class App {
    public static void main(String[] args) {
        // 鼠标对象
        Mouse m = new Mouse();

        // 键盘对象
        KeyBoard keyBoard = new KeyBoard();

        // 主板对象
        MotherBoard board = new MotherBoard();

        // 安插操作
//        board.install("mouse", m);
//        board.install("keyboard", keyBoard);

        // 使用匿名内部类, 安插打印机对象
//        board.install("print", new IUSB() {
//            @Override
//            public void swapData() {
//                System.out.println("打印.......");
//            }
//        });

        // 调用主板的通信功能
        board.doWork();

//        System.out.println("--------------------------");

        // 卸载鼠标
//        board.uninstall("mouse");
//        board.doWork();
    }
}
