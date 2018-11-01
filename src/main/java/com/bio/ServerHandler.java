package com.bio;

import java.io.*;
import java.net.Socket;

/**
 * server 服务器处理方法
 */
public class ServerHandler implements Runnable {
    private Socket socket;

    public ServerHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()) , true);

            //一次只能读一行 in.readLine() 所以要 while（true）
            while (true) {
                String expression;
                String result;
                if ((expression = in.readLine()) == null) break;
                System.out.println("服务器端接受值为：" + expression);
                result = "<" + expression + ">";
                out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
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
