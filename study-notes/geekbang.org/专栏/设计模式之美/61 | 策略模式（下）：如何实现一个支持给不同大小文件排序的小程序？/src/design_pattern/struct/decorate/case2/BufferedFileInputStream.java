package design_pattern.struct.decorate.case2;

import java.io.IOException;
import java.io.InputStream;

public class BufferedFileInputStream extends InputStream {
    public BufferedFileInputStream(String s) {
    }

    @Override
    public int read() throws IOException {
        return 0;
    }
}
