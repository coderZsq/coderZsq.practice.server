package design_pattern.struct.decorate.case4;

import java.io.*;

public class Demo {
    public static void main(String[] args) throws IOException {
        InputStream in = new FileInputStream("/user/wangzheng/test.txt");
        InputStream bin = new BufferedInputStream(in);
        DataInputStream din = new DataInputStream(bin);
        int data = din.readInt();
    }
}
