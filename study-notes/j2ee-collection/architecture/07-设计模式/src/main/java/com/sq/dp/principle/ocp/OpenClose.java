// package com.sq.dp.principle.ocp;
//
// import static com.sq.dp.principle.ocp.AutomobileFactory.AutoType.BENZ;
// import static com.sq.dp.principle.ocp.AutomobileFactory.AutoType.BMW;
// import static com.sq.dp.principle.ocp.AutomobileFactory.AutoType.*;
//
// interface IAutomobile {
//
// }
//
// /**
//  * 开闭原则
//  */
// public class OpenClose {
//     public static void main(String[] args) {
//         AutomobileFactory carf = new AutomobileFactory();
//         carf.createAuto(BMW);
//         carf.createAuto(AUDI);
//         carf.createAuto(BENZ);
//     }
// }
//
// class Audi implements IAutomobile {
//     public Audi() {
//         System.out.println("h1 我是奥迪");
//     }
// }
//
// class BMW implements IAutomobile {
//     public BMW() {
//         System.out.println("hello 我是宝马");
//     }
// }
//
// class BENZ implements IAutomobile {
//     public BENZ() {
//         System.out.println("hello 我是奔驰");
//     }
// }
//
// class AutomobileFactory {
//     public IAutomobile createAuto(AutoType carType) {
//         switch (carType) {
//             case AUDI:
//                 return new Audi();
//             case BMW:
//                 return new BMW();
//             case BENZ:
//                 return new BENZ();
//         }
//         return null;
//     }
//
//     enum AutoType {
//         AUDI, BMW, BENZ
//     }
// }