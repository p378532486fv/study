package handlepackage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName NettyClientHandler
 * @Description 客户端处理器
 * @Author zhangyuchenb
 * @Date 2023/4/24 16:03
 * @Version 1.0
 */
public class HandlePackageClientHandler extends SimpleChannelInboundHandler<String> {


    //当通道有读取事件时会触发，即服务端发送数据给客户端
    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
        System.out.println(msg);
    }

}
