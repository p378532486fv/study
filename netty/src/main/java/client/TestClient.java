package client;

import java.io.*;
import java.net.Socket;

/**
 * @ClassName TestClient
 * @Description Socket客户端
 * @Author zhangyuchenb
 * @Date 2023/4/20 9:27
 * @Version 1.0
 */
public class TestClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost",9090);
        BufferedReader clientReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter serverWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        BufferedReader serverReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        while(true){
            System.out.println("请输入：\t");
            String reqMsg = clientReader.readLine();
            //字符流要手动换行
            reqMsg += "\n";
            serverWriter.write(reqMsg);
            //字符流一定要flush
            serverWriter.flush();
            if("bye".equals(reqMsg)){
                break;
            }
            String respMsg = serverReader.readLine();
            System.out.println("服务端返回消息："+respMsg);
        }
        clientReader.close();
        serverWriter.close();
        serverReader.close();
        socket.close();
    }
}
