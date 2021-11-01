package design_pattern.struct.decorate.case1;

import java.io.*;

public class Demo {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("/user/wangzheng/test.txt");
        InputStream bin = new BufferedInputStream(in);
        byte[] data = new byte[128];
        while (bin.read(data) != -1) {
            //...
        }
    }
}
