package conc;

public class Runner2 implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("进入Runner2运行状态----------" + i);
        }

        boolean result = Thread.currentThread().isInterrupted();

        boolean result1 = Thread.interrupted(); // 重置状态

        // // PThread * pthread = new xxxx;
        // boolean interrupt = false;
        // pthread.run(
        //         while (true) {
        //             for (i = 0; i = 10000L) {
        //                 if (interrupt) {
        //                     break;
        //                 }
        //                 for (j = 0; j = 10000L) {
        //
        //                 }
        //             }
        //         }
        // );
        //
        // interrupt = true;

        boolean result3 = Thread.currentThread().isInterrupted();

        System.out.println("Runner2.run result ===>" + result);
        System.out.println("Runner2.run result1 ===>" + result1);
        System.out.println("Runner2.run result3 ===>" + result3);
    }
}
