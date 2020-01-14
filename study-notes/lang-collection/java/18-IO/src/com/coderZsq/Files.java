package com.coderZsq;

import java.io.File;
import java.util.function.Consumer;

public class Files {
    /*
     * 练习 - 搜索
     * */
    public static void search(File dir, Consumer<File> operation) {
        if (dir == null || operation == null) return;
        if (!dir.exists() || dir.isFile()) return;
        File[] subfiles = dir.listFiles();
        for (File sf : subfiles) {
            operation.accept(sf);
            if (sf.isFile()) continue;
            search(sf, operation);
        }
    }

    /*
     * 练习 - 删除
     * */
    public static void delete(File file) {
        if (file == null || !file.exists()) return;
        clean(file);
        file.delete();
    }

    public static void clean(File dir) {
        if (dir == null || !dir.exists() || dir.isFile()) return;
        File[] subfiles = dir.listFiles();
        for (File sf : subfiles) {
            delete(sf);
        }
    }

    /*
     * 练习 - 剪切
     * */
    private static void mkparents(File file) {
        File parent = file.getParentFile();
        if (parent.exists()) return;
        parent.mkdirs();
    }

    public static void move(File src, File dest) {
        if (src == null || dest == null) return;
        if (!src.exists() || dest.exists()) return;
        mkparents(dest);
        src.renameTo(dest);
    }
}
