package test;

import common.ConnHandle;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName TestDulThreadBIO
 * @Description 多线程BIO
 * @Author zhangyuchenb
 * @Date 2023/4/18 17:42
 * @Version 1.0
 */
public class TestDulThreadBIO {

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

    public static void main(String[] args) throws IOException {
        Socket socket = null;
        try{
            while(true){
                //阻塞
                socket = serverSocket.accept();
                //开辟线程处理请求
                createThread(socket);
            }
        }catch(IOException exception){
            exception.printStackTrace();
        }

    }

    private static void createThread(Socket clientSocket) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //字节流
//                ConnHandle.dealByte(clientSocket);
                //字符流
                ConnHandle.dealChar(clientSocket);
            }
        }).start();
    }
}
