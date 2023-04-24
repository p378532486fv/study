package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName TestSelectorNIO
 * @Description 多路复用器NIO
 * @Author zhangyuchenb
 * @Date 2023/4/20 16:39
 * @Version 1.0
 */
public class TestSelectorNIO {
    public static void main(String[] args) throws IOException {
        //生成服务端
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //绑定端口
        serverSocketChannel.socket().bind(new InetSocketAddress(9090));
        //设置非阻塞
        serverSocketChannel.configureBlocking(false);

        //生成多路复用器
        Selector selector = Selector.open();
        //将服务端注册到多路复用器，进行监听
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while(true){
            //阻塞
            selector.select();

            //同一时间获取的事件集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while(iterator.hasNext()){
                SelectionKey key = iterator.next();
                //连接事件
                if(key.isAcceptable()){
                    //在这里可以创建线程异步处理
                    ServerSocketChannel channel = (ServerSocketChannel)key.channel();
                    //获取客户端连接
                    SocketChannel socket = channel.accept();
                    //设置客户端为非阻塞
                    socket.configureBlocking(false);
                    //添加客户端读事件
                    socket.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功");
                }
                //读事件
                if(key.isReadable()){
                    //在这里可以创建线程异步处理
                    SocketChannel socket = (SocketChannel)key.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    int len = socket.read(buffer);
                    if(len > 0){
                        System.out.println("接收到消息："+new String(buffer.array()));
                    }else if(len != -1){
                        System.out.println("客户端断开连接");
                        socket.close();
                    }
                }
            }



        }

    }
}
