package com.inms.action.socket;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端程序
 * 
 * @author Hongten
 * 
 * @time 2012-4-29 2012
 */
public class server {
    public static void main(String args[]) {
        try {
            // 创建一个socket对象
            ServerSocket s = new ServerSocket(8888);
            // socket对象调用accept方法，等待连接请求
            Socket s1 = s.accept();

            // =========服务器端，在这里应该先打开输出流，在打开输入流，
            // =========因为服务器端执行的操作是先听，再说，听，说，听，说.....

            // 打开输出流
            OutputStream os = s1.getOutputStream();
            // 封装输出流
            DataOutputStream dos = new DataOutputStream(os);
            // 打开输入流
            InputStream is = s1.getInputStream();
            // 封装输入流
            DataInputStream dis = new DataInputStream(is);
            // 创建并启用两个线程
            new MyServerReader(dis).start();
            new MyServerWriter(dos).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 接受并打印客户端传过来的信息
class MyServerReader extends Thread {
    private DataInputStream dis;

    public MyServerReader(DataInputStream dis) {
        this.dis = dis;
    }

    public void run() {
        String info;
        try {
            while (true) {
                // 如果对方，即客户端没有说话，那么就会阻塞到这里，
                // 这里的阻塞并不会影响到其他线程
                info = dis.readUTF();
                // 如果状态有阻塞变为非阻塞，那么就打印接受到的信息
                System.out.println("对方说: " + info);
                if (info.equals("bye")) {
                    System.out.println("对方下线，程序退出!");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// 从键盘获得输入流并写入信息到客户端
class MyServerWriter extends Thread {
    private DataOutputStream dos;

    public MyServerWriter(DataOutputStream dos) {
        this.dos = dos;
    }

    public void run() {
        // 读取键盘输入流
        InputStreamReader isr = new InputStreamReader(System.in);
        // 封装键盘输入流
        BufferedReader br = new BufferedReader(isr);
        String info;
        try {
            while (true) {
                info = br.readLine();
                dos.writeUTF(info);
                if (info.equals("bye")) {
                    System.out.println("自己下线，程序退出!");
                    System.exit(0);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}