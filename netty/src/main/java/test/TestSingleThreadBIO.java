package test;

import common.ConnHandle;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName TestSingleThreadBIO
 * @Description 单线程BIO
 * @Author zhangyuchenb
 * @Date 2023/4/18 9:36
 * @Version 1.0
 */
public class TestSingleThreadBIO {

    private static final int PORT = 9090;
    private static ServerSocket serverSocket = null;

    static{
        //创建ServerSocket，绑定端口
        try {
            serverSocket = new ServerSocket(PORT);
        } catch (IOException e) {
            System.out.println("项目启动创建Socket服务端报错！！！！！！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Socket socket = null;
        try{
            while(true){
                //阻塞
                socket = serverSocket.accept();
                //字节流
//                ConnHandle.dealByte(socket);
                //字符流
                ConnHandle.dealChar(socket);
            }
        }catch(IOException exception){
            exception.printStackTrace();
        }
    }


}
