package com.sq.jk.common.util;

public class Debugs {
    public static final boolean DEBUG = true;

    public static void run(Runnable runnable) {
        if (!DEBUG) return;
        if (runnable == null) return;
        runnable.run();
    }
}
