package com.bio;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    //监控端口号
    private static int DEFAULT_PORT = 9000;
    private static ServerSocket serverSocket = null;

    public static void start() {
        start(DEFAULT_PORT);
    }

    private static synchronized void start(int port) {
//        服务端 serverSocket 为空，就证明没有这个端口的实例。需要new 一个出来。
//        如果已经有了，我就可以跳过这一步直接accepter 获取到客户端传来的 socket
        if (serverSocket != null) return;

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("端口启动:" + port);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                serverSocket = null;
            }
        }

    }


}

