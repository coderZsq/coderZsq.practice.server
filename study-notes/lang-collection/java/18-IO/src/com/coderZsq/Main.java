package com.coderZsq;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

public class Main {

    public static void main(String[] args) {
        /*
         * I/O 流
I/O 流 全称是 Input/Output Stream，译为“输入/输出流” 输出流
         * */

        /*
         * 常用类型
         *
         * I/O 流的常用类型都在 java.io 包中
         *
         * 类型 输入流 输出流
         * 字节流(Byte Streams) InputStream OutputStream
         * 字符流(Character Streams) Reader Writer
         * 缓冲流(Buffered Streams) BufferedInputStream BufferedReader BufferedOutputStream BufferedWriter
         * 数据流(Data Streams) DataInputStream DataOutputStream
         * 对象流(Object Streams)ObjectInputStream ObjectOutputStream
         * */

        /*
         * File
         *
         * 一个 File 对象就代表一个文件或目录(文件夹)
         *
         * 名字分隔符(name separator):File.separator
         * 在 UNIX、Linux、Mac 系统中:正斜杠(/)
         * 在 Windows 系统中:反斜杠(\)
         *
         * 路径分隔符(path separator):File.pathSeparator
         * 在 UNIX、Linux、Mac系统中:冒号(:)
         * 在 Windows 系统中:分号(;)
         *
         * 在 Windows、Mac 系统中
         * 文件名、目录名不区分大小写
         *
         * 在 UNIX、Linux 系统中
         * 文件名、目录名区分大小写
         * */
        {
            // file1, file2都能访问Main.java文件
            File file1 = new File("F:\\Files\\Texts\\test.txt");
            File file2 = new File("F:/Files/Tests/test.txt");
        }

        /*
         * File - 常用方法
         * */
        {
            // String getName() // 获取文件或目录的名称
            // String getParent() // 获取父路径
            // File getParentFile() // 获取父文件
            // String getPath() // 获取路径
            // String getAbsolutePath() // 获取绝对路径
            // File getAbsoluteFile() // 获取绝对路径形式的文件
            // long lastModified() // 最后一次修改的时间
            // long length() // 文件的大小 (不支持目录)
        }

        {
            // boolean isAbsolute()
            // boolean exists()
            // boolean isDirectory()
            // boolean isFile()
            // boolean isHidden()
            // boolean canRead()
            // boolean canWrite()
        }

        {
            // String[] list() // 获取当前目录下所有文件, 目录的名称
            // String[] list(FilenameFilter filter)
            // File[] listFiles() // 获取当前目录下所有文件, 目录
            // File[] listFiles(FilenameFilter filter)
            // File[] listFiles(FileFilter filter)
        }

        {
            // boolean createNewFile() // 创建文件 (不会覆盖旧文件)
            // boolean delete() // 删除文件或空目录 (不经过回收站)
            // boolean mkdir() // 创建当前目录
            // boolean mkdirs() // 创建当前目录 (包括不存在的父目录)
            // boolean renameTo(File dest) // 剪切到新路径
        }

        {
            // boolean setLastModified(long time)
            // boolean setReadOnly()
            // boolean setWritable(boolean writable, boolean ownerOnly)
            // boolean setWritable(boolean writable)
            // boolean setReadable(boolean readable, boolean ownerOnly)
            // boolean setReadable(boolean readable)
        }
    }


}
