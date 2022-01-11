package com.example.testarea.Utils;


import java.io.DataInputStream;//导入DataInputStream类
import java.io.DataOutputStream;//导入DataOutputStream
import java.io.IOException;//导入IOException类
import java.net.Socket;//导入Socket类
import java.util.Scanner;//导入Scanner类

/**
 * 注意用到的输入输出流DataInputStream和DataOutputStream，成对出现，最好用字节流
 */
// 客户端类
public class Tcpchat {//创建公共类
    private String host = "192.168.1.8";// 默认连接到本机
    private int port = 8189;// 默认连接到端口8189

    public Tcpchat() {

    }

    // 连接到指定的主机和端口
    public Tcpchat(String host, int port) {//构造方法
        this.host = host;//将构造方法的参数host传递给类变量host
        this.port = port;//将构造方法的参数port传递给类变量port
    }

    public void chat(String msg) {//chat方法
            // 连接到服务器
            new Thread(
                    () -> {
                            // 把从控制台得到的信息传送给服务器
                        Socket socket = null;//创建Socket类对象
                        try {
                            System.out.println("kkkkkkkkkkkkk");
                            socket = new Socket(host, port);
                            DataInputStream in = new DataInputStream(socket
                                    .getInputStream());// 读取服务器端传过来信息的DataInputStream

                            DataOutputStream out = new DataOutputStream(socket
                                    .getOutputStream());// 向服务器端发送信息的DataOutputStream
                            out.writeUTF("客户端：" + msg);//将客户端的信息传递给服务器
                            String accpet = in.readUTF();// 读取来自服务器的信息
                            System.out.println(accpet);//输出来自服务器的信息
//                            socket.close();//关闭Socket监听
                            System.out.println("iiiiiiiiiiiiiiiiii");

                            } catch (IOException e) {
                                e.printStackTrace();
                            }finally {
                            System.out.println("---------------close----------------");
                        }
                    }
            ).start();
    }

}


