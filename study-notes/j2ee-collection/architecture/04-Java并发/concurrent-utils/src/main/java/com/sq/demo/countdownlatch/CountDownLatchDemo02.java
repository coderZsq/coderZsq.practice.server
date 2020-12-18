package com.sq.demo.countdownlatch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.*;

/**
 * 1 使用多线程的方式 读取指定文件夹里面所有的txt文件, 统计每个单词出现的数量
 * 2 统计完成后, 最后汇总每个单词出现的次数
 * <p>
 * a 使用线程池 ThreadPoolExecutor
 * b 字符文件输入流 BufferedReader
 * c 使用Map保存对应的数据
 */
public class CountDownLatchDemo02 {
    public static void main(String[] args) throws InterruptedException {
        // 1 创建一个线程池
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(16, 16, 0, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        // 3 定义一个list, 用来保存所有任务的一个结果
        final ConcurrentLinkedDeque<HashMap<String, Integer>> list = new ConcurrentLinkedDeque<>();
        // 4 定义读取文件的目录
        File dir = new File("/Users/zhushuangquan/Codes/GitHub/coderZsq.practice.server/study-notes/j2ee-collection/architecture/04-Java并发/concurrent-utils/src/main/resources");
        // 获取到所有文件
        final File[] files = dir.listFiles();
        final int count = files.length;
        // 5 定义个计数器
        final CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 0; i < count; i++) {
            executor.execute(new TaskInfo(files[i], list, countDownLatch));
        }
        executor.shutdown();
        // 汇总结果
        countDownLatch.await();
        final HashMap<String, Integer> total = new HashMap<>();
        for (HashMap<String, Integer> hashMap : list) {
            for (String key : hashMap.keySet()) {
                final Integer oldValue = total.getOrDefault(key, 0);
                total.put(key, oldValue + hashMap.get(key));
            }
        }
        System.out.println("所有的数据汇总结果: " + total);
    }

    // 2 定义需要执行的任务 Runnable接口
    static class TaskInfo implements Runnable {
        // 需要读取的文件
        File file = null;
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>();
        ConcurrentLinkedDeque<HashMap<String, Integer>> list = null;
        CountDownLatch countDownLatch = null;

        public TaskInfo(File file, ConcurrentLinkedDeque<HashMap<String, Integer>> list, CountDownLatch countDownLatch) {
            this.file = file;
            this.list = list;
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            // 创建文件流对象读取文件
            try (
                    FileReader fr = new FileReader(file);
                    BufferedReader br = new BufferedReader(fr)
            ) {
                String line = null; // 读取到文件内容
                while ((line = br.readLine()) != null) {
                    final String[] words = line.split(" ");
                    for (String key : words) {
                        final Integer oldValue = hashMap.getOrDefault(key, 0);
                        hashMap.put(key, oldValue + 1);
                    }
                }
                list.add(hashMap);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
