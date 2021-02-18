package com.sq.io.aio;

import java.io.IOException;

/**
 * @author Leon
 * @date 2021/1/1
 */
public class Bootstrap {
    public  static void main(String[] args) throws IOException {
        new Thread(new AioServer(8080)).start();
    }
}
