package test;

import common.ConnHandle;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * @ClassName TestThreadPoolBIO
 * @Description 线程池BIO
 * @Author zhangyuchenb
 * @Date 2023/4/18 17:42
 * @Version 1.0
 */
public class TestThreadPoolBIO {

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
                //线程池处理请求
                threadPoolHandle(socket);
            }
        }catch(IOException exception){
            exception.printStackTrace();
        }

    }

    private static void threadPoolHandle(Socket clientSocket) {
        //核心线程数
        int pcount = 1;
        //最大线程数控制
        int maxthreadNum = 1;
        //设置的是无界队列，基本不会有连接不上的情况
        ExecutorService executor = new ThreadPoolExecutor(pcount, maxthreadNum, 10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                //字节流
                ConnHandle.dealByte(clientSocket);
                //字符流
//                ConnHandle.dealChar(clientSocket);
            }
        });
    }
}
