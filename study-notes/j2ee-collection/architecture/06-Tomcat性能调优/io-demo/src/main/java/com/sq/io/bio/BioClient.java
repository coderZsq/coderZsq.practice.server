package com.sq.io.bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Leon
 * @date 2021/1/1
 */
public class BioClient {

    private static int SERVER_PORT = 8080;
    private static String SERVER_IP = "127.0.0.1";

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            socket = new Socket(SERVER_IP, SERVER_PORT);
            String hello = "hello server";
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(hello.getBytes());
            outputStream.flush();
            InputStream inputStream = socket.getInputStream();
            byte[] rev = new byte[1024];
            inputStream.read(rev);
            System.out.println("client receive: " + new String(rev));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                in = null;
            }
            if (in != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out = null;
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }
    }
}
