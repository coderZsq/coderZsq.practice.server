package conc.tool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class CyclicBarrierTask implements Runnable {
    private CyclicBarrier barrier;

    public CyclicBarrierTask(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    // 使用
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int num = 2; // 如果数量过大会发生什么情况?
        CyclicBarrier barrier = new CyclicBarrier(num);
        List<CompletableFuture> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(new CyclicBarrierTask(barrier));
            list.add(future);
        }
        for (CompletableFuture future : list) {
            future.get();
        }
        barrier.reset();
    }

    @Override
    public void run() {
        Integer millis = new Random().nextInt(10000);
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
            this.barrier.await(); // 线程阻塞
            System.out.println("开吃:" + Thread.currentThread().getName());
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
