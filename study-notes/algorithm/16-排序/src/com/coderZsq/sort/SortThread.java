package com.coderZsq.sort;

public class SortThread extends Thread {
    private int value;
    public SortThread(int value) {
        this.value = value;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(value);
            System.out.println(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int[] array = {10, 100, 50, 30, 60};
        for (int i = 0; i < array.length; i++) {
            new SortThread(array[i]).start();
        }
    }
}

