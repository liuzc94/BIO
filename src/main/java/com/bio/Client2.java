package com.bio;

import java.io.*;
import java.net.Socket;

/**
 * 客户端2
 */
public class Client2 {
    private static int SEND_PORT = 9000;
    private static String SERVER_IP = "127.0.0.1";


    public static void start(String expression) {
        startSend(expression);
    }

    private static synchronized void startSend(String expression) {
        BufferedReader in = null;
        PrintWriter out = null;
        Socket socket = null;

        try {
            socket = new Socket(SERVER_IP,SEND_PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        out.println("Client2:----------------" + expression);
        try {
            System.out.println("客户端收到 服务端返回值：" + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                in = null;
            }

            if (out != null) {
                out.close();
                out = null;
            }

            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                socket = null;
            }
        }

    }
}
