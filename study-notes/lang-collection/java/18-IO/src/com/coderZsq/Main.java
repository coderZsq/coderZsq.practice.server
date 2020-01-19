package com.coderZsq;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        /*
         * I/O 流
         *
         * I/O 流 全称是 Input/Output Stream，译为“输入/输出流” 输出流
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

        /*
         * 字符集(Character Set)
         *
         * 在计算机里面
         * 一个中文汉字是一个字符
         * 一个英文字母是一个字符
         * 一个阿拉伯数字是一个字符
         * 一个标点符号是一个字符
         * ......
         *
         * 字符集(简称 Charset):由字符组成的集合
         *
         * 常见的字符集有
         * ASCII:128个字符(包括了英文字母大小写、阿拉伯数字等)
         * ISO-8859-1:支持欧洲的部分语言文字，在有些环境也叫 Latin-1
         * GB2312:支持中文(包括了 6763 个汉字)
         * BIG5:支持繁体中文(包括了 13053 个汉字)
         * GBK:是对 GB2312、BIG5 的扩充(包括了 21003 个汉字)，支持中日韩
         * GB18030:是对 GBK 的扩充(包括了 27484 个汉字)
         * Unicode:包括了世界上所有的字符
         *
         * ISO-8859-1、GB2312、BIG5、GBK、GB18030、Unicode 中都已经包括了 ASCII 中的所有字符
         * */

        /*
         * 字符编码(Character Encoding)
         *
         * 每个字符集都有对应的字符编码，它决定了每个字符如何转成二进制存储在计算机中
         *
         * ASCII:单字节编码，编码范围是 0x00 ~ 0x7F (0 ~ 127)
         *
         * ISO-8859-1:单字节编码，编码范围是 0x00 ~ 0xFF
         * 0x00 ~ 0x7F 和 ASCII 一致，0x80 ~ 0x9F 是控制字符，0xA0 ~ 0xFF 是文字符号
         *
         * GB2312、BIG5、GBK:采用双字节表示一个汉字
         *
         * GB18030:采用单字节、双字节、四字节表示一个字符
         *
         * Unicode:有 Unicode、UTF-8、UTF-16、UTF-32 等编码，最常用的是 UTF-8 编码
         * UTF-8 采用单字节、双字节、三字节、四字节表示一个字符
         * */

        /*
         * 字符编码比较
         *
         * 如果 String.getBytes 方法没有传参，就使用 JVM 的默认字符编码，一般跟随 main 方法所在文件的字符编码
         *
         * 可以通过 Charset.defaultCharset 方法获取 JVM 的默认字符编码
         * Charset 类的全名是 java.nio.charset.Charset
         * */
        {
            String str = "SQ双泉";
            str.getBytes("ASCII"); // [83, 81, 63, 63]
            str.getBytes("ISO-8859-1"); // [83, 81, 63, 63]
            str.getBytes("GB2312"); // [83, 81, -53, -85, -56, -86]
            str.getBytes("BIG5"); // [83, 81, 63, -84, 117]
            str.getBytes("GBK"); // [83, 81, -53, -85, -56, -86]
            str.getBytes("GB18030"); // [83, 81, -53, -85, -56, -86]
            str.getBytes("UTF-8"); // [83, 81, -27, -113, -116, -26, -77, -119]
        }

        /*
         * 乱码
         *
         * 一般将【字符串】转为【二进制】的过程称为:编码(Encode)
         * 一般将【二进制】转为【字符串】的过程称为:解码(Decode)
         *
         * 编码、解码时使用的字符编码必须要保持一致，否则会造成乱码
         * */
        {
            String str1 = "Java不难";
            // 编码: [74, 97, 118, 97, -28, -72, -115, -23, -102, -66]
            byte[] bytes = str1.getBytes("UTF-8");
            // 编码: Java涓嶉毦
            String str2 = new String(bytes, "GB18030");
        }

        /*
         * 字节流(Byte Streams)
         *
         * 字节流的特点
         * 一次只读写一个字节
         * 最终都继承自 InputSteam、OutputStream
         *
         * 常用的是字节流有 FileInputStream、FileOutputStream
         * */

        /*
         * 字节流结构预览
         *
         * AutoCloseable
         *
         * Closeable
         *
         * OutputStream
         * FilterOutputStream FileOutputStream
         * BufferedOutputStream
         * Flushable
         *
         * InputStream
         * FileInputStream FilterInputStream
         *                 BufferedInputStream
         * */

        /*
         * FileOutputStream
         * */
        if (false) {
            OutputStream os = new FileOutputStream("./1.txt");
            os.write(83); // S
            os.write(81); // Q
            os.close();
        }

        if (false) {
            // true表示追加内容, 并非覆盖原来数据
            OutputStream os = new FileOutputStream("./2.txt", true);
            os.write("SQ双泉".getBytes());
            os.close();
        }

        /*
         * FileInputStream
         * */
        if (false) {
            InputStream is = new FileInputStream("./2.txt");
            // 读取第1个字节
            int byte1 = is.read();
            // 读取第2个字节
            int byte2 = is.read();
            is.close();
        }

        if (false) {
            InputStream is = new FileInputStream("./2.txt");
            byte[] bytes = new byte[1024];
            // read返回实际读取的字节数
            int len = is.read(bytes);
            is.close();
        }

        /*
         * try-with-resources 语句
         *
         * 下图就是从 Java 7 开始推出的 try-with-resources 语句(可以没有 catch、finally )
         *
         * 可以在 后面的小括号中声明一个或多个资源(resource)
         * 实现了 java.lang.AutoCloseable 接口的实例，都可以称之为是资源
         *
         * 不管 中的语句是正常还是意外结束
         * 最终都会自动按顺序调用每一个资源的 close 方法(close 方法的调用顺序与资源的声明顺序相反)
         * 调用完所有资源的 close 方法后，再执行 finally 中的语句
         * */
        {
            // try (资源1; 资源2; ...) {
            //
            // } catch (Exception e) {
            //
            // } finally {
            //
            // }
        }

        /*
         * 字符流(Character Streams)
         *
         * 字符流的特点
         * 一次只读写一个字符
         * 最终都继承自 Reader、Writer
         *
         * 常用的是字符流有 FileReader、FileWriter
         * 注意:这 2 个类只适合文本文件，比如 .txt、.java 等这类文件
         * */

        /*
         * 字符流结构预览
         *
         * AutoCloseable
         * Closeable
         *
         * Writer
         * OutputStreamWriter BufferedWriter
         * FileWriter
         * Appendable
         * Flushable
         *
         * Reader
         * BufferedReader InputStreamReader
         *                FileReader
         * Readable
         * */

        /*
         * FileWriter
         * */
        if (false) {
            Writer writer = new FileWriter("./2.txt");
            writer.write("S");
            writer.write("Q");
            writer.write("双");
            writer.write("泉");
            writer.close();
        }

        if (false) {
            Writer writer = new FileWriter("./2.txt");
            writer.write("SQ");
            writer.write("双泉".toCharArray());
            writer.close();
        }

        /*
         * FileReader
         * */
        if (false) {
            Reader reader = new FileReader("./2.txt");
            // 读取第1个字符
            int c1 = reader.read();
            // 读取第2个字符
            int c2 = reader.read();
            reader.close();
        }

        if (false) {
            Reader reader = new FileReader("./2.txt");
            char[] chars = new char[1024];
            // read方法返回实际读取的字符数
            int len = reader.read(chars);
            reader.close();
        }

        /*
         * 练习 - 将文本文件的内容逐个字符打印出来
         * */
        if (false) {
            File file = new File("./2.txt");
            try (
                    Reader reader = new FileReader(file)
            ) {
                int c;
                while ((c = reader.read()) != -1) {
                    System.out.println((char) c);
                    Thread.sleep(10);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
         * 缓冲流(Buffered Streams)
         *
         * 之前学习的字节流、字符流，都是无缓冲的 I/O 流，每个读写操作均由底层操作系统直接处理
         * 每个读写操作通常会触发磁盘访问，因此大量的读写操作，可能会使程序的效率大大降低
         *
         * 为了减少读写操作带来的开销，Java 实现了缓冲的 I/O 流
         * 缓冲输入流:从缓冲区读取数据，并且只有当缓冲区为空时才调用本地的输入 API(Native Input API)
         * 缓冲输出流:将数据写入缓冲区，并且只有当缓冲区已满时才调用本地的输出 API(Native Output API)
         *
         *           缓冲输入流           缓冲输出流
         * 缓冲字节流 BufferedInputStream BufferedOutputStream
         * 缓冲字符流 BufferedReader      BufferedWriter
         *
         * 上述表格中 4 个缓冲流的默认缓冲区大小是 8192 字节(8KB)，可以通过构造方法传参设置缓冲区大小
         * */

        /*
         * 缓冲流 – 使用
         *
         * 缓冲流的常见使用方式:将无缓冲流传递给缓冲流的构造方法(将无缓冲流包装成缓冲流)
         * 如果把无缓冲流比作是一个无装备的士兵，那么缓冲流就是一个有强力装备的士兵
         * */
        if (false) {
            File file = new File("./2.txt");
            InputStream is = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(is, 16384);
            bis.close();
        }

        if (false) {
            File file = new File("./2.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("111");
            writer.newLine();
            writer.write("222");
            writer.close();
        }

        /*
         * 缓冲流 – close、flush
         *
         * 只需要执行缓冲流的 close 方法，不需要执行缓冲流内部包装的无缓冲流的 close 方法
         *
         * 调用缓冲输出流的 flush 方法，会强制调用本地的输出 API，将缓冲区的数据真正写入到文件中
         * 缓冲输出流的 close 方法内部会调用一次 flush 方法
         * */

        /*
         * 练习 - 用缓冲流逐行打印字符串
         * */
        if (false) {
            File file = new File("./1.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                    Thread.sleep(100);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /*
         * 练习 - 转换文本文件编码
         * */
        if (false) {
            try (
                    BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./gbk.txt"), "GBK"));
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("./utf-8.txt"), "UTF-8"));
            ) {
                char[] chars = new char[1024];
                int len;
                while ((len = reader.read(chars)) != -1) {
                    writer.write(chars, 0, len);
                }
            }
        }

        /*
         * 练习 – “价值几百万”的 AI 代码
         *
         * System.in 属于标准输入流，可以从键盘接收输入
         * 利用 InputStreamReader 可以实现【字节输入流】转【字符输入流】
         * 同理，利用 OutputStreamWriter 可以实现【字节输出流】转【字符输出流】
         * */
        if (false) {
            // InoutStream -> InputStreamReader -> BufferedReader
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String str;
            while ((str = reader.readLine()) != null) {
                str = str.replace("你", "朕");
                str = str.replace("吗", "");
                str = str.replace("么", "");
                str = str.replace("?", "!");
                str = str.replace("? ", "!");
                System.out.println("\t" + str);
            }
            reader.close();
        }

        /*
         * Scanner
         *
         * java.util.Scanner 是一个可以使用正则表达式来解析基本类型和字符串的简单文本扫描器
         * 它默认利用空白(空格\制表符\行终止符)作为分隔符将输入分隔成多个 token
         * */
        {
            // Scanner(InputStream source)
            // Scanner(Readable source)
            // Scanner(File source)
            // Scanner(String source)
        }

        {
            Scanner s = new Scanner("jack rose kate");
            while (s.hasNext()) {
                System.out.println(s.next());
            }
            // jack
            // rose
            // kate
            s.close();
        }

        if (false) {
            Scanner s = new Scanner(new File("./1.txt"));
        }

        /*
         * Scanner - next
         * */
        {
            Scanner s = new Scanner("jack 666 888 ak47");
            System.out.println(s.next()); // jack
            System.out.println(s.nextInt()); // 666
            System.out.println(s.nextDouble()); // 888
            System.out.println(s.next("[a-z]{2}\\d{2}")); // ak47
            s.close();
        }

        /*
         * Scanner – useDelimiter
         *
         * Scanner.useDelimiter 方法可以自定义分隔符
         * */
        {
            Scanner s = new Scanner("aa 1 bb 22 cc33dd");
            s.useDelimiter("\\s*\\d+\\s*");
            while (s.hasNext()) {
                System.out.println(s.next());
            }
            // aa bb cc dd
            s.close();
        }

        {
            Scanner s = new Scanner("aa11bb22cc");
            s.useDelimiter("");
            while (s.hasNext()) {
                System.out.println(s.next());
            } // a a 1 1 b b 2 2 c c
            s.close();
        }

        /*
         * Scanner - 标准输入流
         * */
        if (false) {
            Scanner s = new Scanner(System.in);
            System.out.print("请输入第1个整数: ");
            int n1 = s.nextInt();
            System.out.print("请输入第2个整数: ");
            int n2 = s.nextInt();
            System.out.format("%d + %d = %d\n", n1, n2, n1 + n2);
            s.close();
            /*
             请输入第1个整数: 6
             请输入第2个整数: 8
             6 + 8 = 14
             */
        }

        /*
         * Scanner – “价值几百万”的 AI 代码
         * */
        if (false) {
            Scanner s = new Scanner(System.in);
            while (s.hasNextLine()) {
                String str = s.nextLine();
                str = str.replace("你", "朕");
                str = str.replace("吗", "");
                str = str.replace("么", "");
                str = str.replace("?", "!");
                str = str.replace("? ", "!");
                System.out.println("\t" + str);
            }
            s.close();
        }

        /*
         * 格式化输出
         *
         * 有 2 个类可以实现格式化输出
         * PrintStream、PrintWriter
         *
         * 它们有 3 个常用方法:print、println、format
         *
         * print、write 的区别
         * write(97) 写入的是字符 'a'
         * print(97) 写入的是字符 "97"
         *
         * AutoCloseable
         * Closeable
         * OutputStream
         * FilterOutputStream
         * PrintStream
         * Flushable
         * Appendable
         *
         * AutoCloseable
         * Closeable
         * Writer
         * PrintWriter
         * Appendable
         * Flushable
         * */

        /*
         * PrintStream
         *
         * System.out、System.err 是 PrintStream 类型的实例
         * 属于标准输出流(Standard Ouput Stream)
         * 比如输出到屏幕、控制台(Console)
         *
         * PrintStream 是字节流，但它内部利用字符流对象来模拟字符流的许多功能
         * */

        /*
         * PrintWriter
         *
         * 平时若要创建格式化的输出流，一般使用 PrintWriter，它是字符流
         *
         * 可以通过构造方法设置 PrintWriter.autoflush 为
         * 那么 println、printf、format 方法内部就会自动调用 flush 方法
         * */
        if (false) {
            String name = "Jack";
            int age = 20;

            PrintWriter writer = new PrintWriter("./2.txt");
            writer.format("My name is %s, age is %d", name, age);
            writer.close();
        }

        if (false) {
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File("./2.txt")), true);
        }

        /*
         * 数据流
         *
         * 有 2 个数据流:DataInputStream、DataOutputStream，支持基本类型、字符串类型的 I / O 操作
         * */
        {
            int age = 20;
            int money = 3000;
            double height = 1.75;
            String name = "Jack";
            DataOutputStream dos = new DataOutputStream(new FileOutputStream("./2.txt"));
            dos.writeInt(age);
            dos.writeInt(money);
            dos.writeDouble(height);
            dos.writeUTF(name);
            dos.close();
            // 文件内容: 0000 0014 0000 0bb8 3ffc 0000 0000 0000 0004 4a61 636b
        }

        {
            DataInputStream dis = new DataInputStream(new FileInputStream("./2.txt"));
            System.out.println(dis.readInt()); // 20
            System.out.println(dis.readInt()); // 3000
            System.out.println(dis.readDouble()); // 1.75
            System.out.println(dis.readUTF()); // Jack
            dis.close();
        }

        /*
         * 数据流、对象流结构预览
         *
         *                                AutoCloseable
         *                                  Closeable
         *              OutputStream                         InputStream
         * FilterOutputStream ObjectOutputStream ObjectInputStream FilterInputStream
         * DataOutputStream   ObjectOutput       ObjectInput       DataInputStream
         * DataOutput                  ObjectStreamConstants       DataInput
         *               Flushable
         * */

        /*
         * 对象流
         *
         * 有 2 个对象流:ObjectInputStream、ObjectOutputStream，支持引用类型的 I / O 操作
         *
         * 只有实现了 java.io.Serializable 接口的类才能使用对象流进行 I / O 操作
         * 否则会抛出 java.io.NotSerializableException 异常
         *
         * Serializable 是一个标记接口(Maker Interface)，不要求实现任何方法
         * */

        /*
         * 对象的序列化和反序列化
         *
         * 序列化(Serialization)
         * 将对象转换为可以存储或传输的数据
         * 利用 ObjectOutputStream 可以实现对象的序列化
         *
         * 反序列化(Deserialization )
         * 从序列化后的数据中恢复出对象
         * 利用 ObjectInputStream 可以实现对象的反序列化
         *
         * 若将对象比作是一座冰雕
         * 序列化:将冰雕融化成水
         * 反序列化:将融化后的水恢复成冰雕
         * */
    }
}
