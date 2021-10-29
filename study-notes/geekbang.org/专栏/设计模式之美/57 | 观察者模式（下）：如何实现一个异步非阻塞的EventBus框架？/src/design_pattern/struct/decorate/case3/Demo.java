package design_pattern.struct.decorate.case3;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class Demo {
    public static void main(String[] args) throws IOException {
        FileInputStream in = new FileInputStream("/user/wangzheng/test.txt");
        DataInputStream din = new DataInputStream(in);
        int data = din.readInt();
    }
}
