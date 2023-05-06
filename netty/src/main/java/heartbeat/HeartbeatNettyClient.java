package heartbeat;

import chat.NettyClientHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Random;
import java.util.Scanner;

/**
 * @ClassName HeartbeatNettyClient
 * @Description netty客户端
 * @Author zhangyuchenb
 * @Date 2023/4/24 16:02
 * @Version 1.0
 */
public class HeartbeatNettyClient {
    public static void main(String[] args) throws Exception {
        //客户端需要一个事件循环组
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            //创建客户端启动对象
            //注意客户端使用的不是 ServerBootstrap 而是 Bootstrap
            Bootstrap bootstrap = new Bootstrap();
            //设置相关参数
            bootstrap.group(group) //设置线程组
                    .channel(NioSocketChannel.class) // 使用 NioSocketChannel 作为客户端的通道实现
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            //加入处理器
                            channel.pipeline().addLast("encoder",new StringEncoder());
                            channel.pipeline().addLast("decoder",new StringDecoder());
                            channel.pipeline().addLast(new HeartbeatClientHandler());
                        }
                    });
            //启动客户端去连接服务器端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 9000).sync();
            Channel channel = channelFuture.channel();
            System.out.println("已进入聊天室");

            //从控制台循环输入
//            Scanner scanner = new Scanner(System.in);
//            while(scanner.hasNextLine()){
//                String msg = scanner.nextLine();
//                channel.writeAndFlush(msg);
//            }

            Random random = new Random();
            String backagemsg = "backage msg";
            //只要没断开就一直循环
            while(channel.isActive()){
                int num = random.nextInt(10);
                Thread.sleep(num * 1000);
                channel.writeAndFlush(backagemsg);
            }

            //对关闭通道进行监听
//            channelFuture.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
