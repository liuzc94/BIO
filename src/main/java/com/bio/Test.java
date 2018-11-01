package com.bio;

import java.util.Random;

/**
 * 测试类方法
 * Server 端是通过监控端口的方式来获取的
 * 模拟两个客户端用9000 分别 向Server 端发消息 。
 *
 */
public class Test {

    public static void main(String[] args) {
        //启动服务器
        new Thread(new Runnable() {
            public void run() {
                Server.start();
            }
        }).start();

        final Random random = new Random(System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //Client1发送
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Client1.start(String.valueOf(random.nextInt()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        //Client2发送
        new Thread(new Runnable() {
            public void run() {
                while (true) {
                    Client2.start(String.valueOf(random.nextInt()));
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
