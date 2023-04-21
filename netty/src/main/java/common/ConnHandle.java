package common;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName ConnHandle
 * @Description 连接处理类
 * @Author zhangyuchenb
 * @Date 2023/4/20 9:44
 * @Version 1.0
 */
public class ConnHandle {

    /**
    * @author zhangyuchenb
    * @Description 处理字符流请求类
    * @Date 2023/4/20 9:45
    * @Param  socket
    * @return void
     */
    public static void dealChar(Socket socket) {
        BufferedReader clientReader = null;
        BufferedReader serverReader = null;
        OutputStreamWriter clientWriter = null;
        try{
            System.out.println("连接的客户端地址："+socket.getInetAddress().getHostAddress());
            System.out.println("连接的客户端名称："+socket.getInetAddress().getHostName());
            //客户端输入流
            clientReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //服务端输入流
            serverReader = new BufferedReader(new InputStreamReader(System.in));
            //输出流
            clientWriter = new OutputStreamWriter(socket.getOutputStream());
            while(true){
                //阻塞
                String reqMsg = clientReader.readLine();
                System.out.println("收到客户端信息："+reqMsg);
                //设置连接结束
                if("bye".equals(reqMsg)){
                    break;
                }
                System.out.println("请输入：\t");
                String respMsg = serverReader.readLine();
                //字符流要手动换行
                respMsg += "\n";
                clientWriter.write(respMsg);
                //字符流一定要flush
                clientWriter.flush();
            }
        }catch(IOException e){
            System.out.println("服务器处理字节流信息报错！！！！！！");
            e.printStackTrace();
        }finally {
            if(clientReader != null){
                try{
                    clientReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(serverReader != null){
                try{
                    serverReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(clientWriter != null){
                try{
                    clientWriter.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try{
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    /**
    * @author zhangyuchenb
    * @Description 处理字节流请求类
    * @Date 2023/4/20 9:45
    * @Param socket
    * @return void
    **/
    public static void dealByte(Socket socket) {
        BufferedInputStream clientInputStream = null;
        BufferedInputStream serverInputStream = null;
        OutputStream outputStream = null;
        try{
            System.out.println("连接的客户端地址："+socket.getInetAddress().getHostAddress());
            System.out.println("连接的客户端名称："+socket.getInetAddress().getHostName());
            //客户端输入流
            clientInputStream = new BufferedInputStream(socket.getInputStream());
            //服务端输入流
            serverInputStream = new BufferedInputStream(System.in);
            //输出流
            outputStream = socket.getOutputStream();
            while(true){
                byte[] bytes = new byte[1024];
                byte[] bytes2 = new byte[1024];
                //阻塞
                clientInputStream.read(bytes);

                //精简bytes
                int i = 0;
                for(byte b:bytes){
                    if(b == 0){
                        break;
                    }
                    i++;
                }
                String reqMsg = new String(bytes,0,i);
                System.out.println("收到客户端信息：***"+reqMsg+"***");
                //设置连接结束
                if("bye".equals(reqMsg)){
                    break;
                }
                System.out.println("请输入：\t");
                serverInputStream.read(bytes2);
                outputStream.write(bytes2);
            }
        }catch(IOException e){
            System.out.println("服务器处理字节流信息报错！！！！！！");
            e.printStackTrace();
        }finally {
            if(clientInputStream != null){
                try{
                    clientInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(serverInputStream != null){
                try{
                    serverInputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(outputStream != null){
                try{
                    outputStream.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(socket != null){
                try{
                    socket.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
