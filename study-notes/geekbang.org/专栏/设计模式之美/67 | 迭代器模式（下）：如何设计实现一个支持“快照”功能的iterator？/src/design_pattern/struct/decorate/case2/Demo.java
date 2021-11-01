package design_pattern.struct.decorate.case2;

import java.io.IOException;
import java.io.InputStream;

public class Demo {
    public static void main(String[] args) throws IOException {
        InputStream bin = new BufferedFileInputStream("/user/wangzheng/test.txt");
        byte[] data = new byte[128];
        while (bin.read(data) != -1) {
            //...
        }
    }
}
