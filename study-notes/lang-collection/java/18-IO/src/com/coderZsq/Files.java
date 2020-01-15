package com.coderZsq;

import java.io.*;
import java.util.function.Consumer;

public class Files {
    private static final int EOF = -1;

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
        if (parent == null) return;
        parent.mkdirs();
    }

    public static void move(File src, File dest) {
        if (src == null || dest == null) return;
        if (!src.exists() || dest.exists()) return;
        mkparents(dest);
        src.renameTo(dest);
    }

    /*
     * 练习 - 将内存中的数据写入文件
     * */
    public static void write1(byte[] data, File file) {
        if (data == null || file == null || file.exists()) return;
        mkparents(file);
        try (
                OutputStream os = new FileOutputStream(file);
        ) {
            os.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 练习 - 用缓冲流修改 write
     * */
    public static void write(byte[] data, File file) {
        if (data == null || file == null || file.exists()) return;
        mkparents(file);
        try (OutputStream os = new BufferedOutputStream(new FileOutputStream(file))) {
            os.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 练习 - 从文件读取数据到内存
     * */
    public static byte[] read1(File file) {
        if (file == null || !file.exists()) return null;
        if (file.isDirectory()) return null;
        try (InputStream is = new FileInputStream(file);) {
            byte[] data = new byte[(int) file.length()];
            is.read(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 练习 - 用缓冲流修改 read
     * */
    public static byte[] read(File file) {
        if (file == null || !file.exists()) return null;
        if (file.isDirectory()) return null;
        try (InputStream is = new BufferedInputStream(new FileInputStream(file))) {
            byte[] data = new byte[(int) file.length()];
            is.read(data);
            return data;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 练习 - 复制
     * */
    public static void copy1(File src, File dest) {
        if (src == null || dest == null) return;
        if (!src.exists() || dest.exists()) return;
        if (src.isDirectory()) return;
        mkparents(dest);
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(src);
            os = new FileOutputStream(dest);
            byte[] data = new byte[8192];
            int len;
            while ((len = is.read(data)) != -1) {
                os.write(data, 0, len);
            }
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                }
            }
        }
    }

    /*
     * try-with-resources 语句 - 示例
     * */
    public static void copy2(File src, File dest) {
        if (src == null || dest == null) return;
        if (!src.exists() || dest.exists()) return;
        if (src.isDirectory()) return;
        mkparents(dest);
        try (
                InputStream is = new FileInputStream(src);
                OutputStream os = new FileOutputStream(dest)) {
            byte[] data = new byte[8192];
            int len;
            while ((len = is.read(data)) != -1) {
                os.write(data, 0, len);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * 练习 - 用缓冲流修改copy
     * */
    public static void copy(File src, File dest) {
        if (src == null || dest == null || !src.exists() || dest.exists()) return;
        if (src.isDirectory()) return;
        mkparents(dest);
        try (
                InputStream is = new BufferedInputStream(new FileInputStream(src));
                OutputStream os = new BufferedOutputStream(new FileOutputStream(dest));
        ) {
            byte[] buffer = new byte[8192];
            int len;
            while ((len = is.read(buffer)) != EOF) {
                os.write(buffer, 0, len);
            }
        } catch (FileNotFoundException e) { e.printStackTrace(); }
        catch (IOException e) { e.printStackTrace(); }
    }
}
