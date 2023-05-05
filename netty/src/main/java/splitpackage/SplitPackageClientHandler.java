package splitpackage;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @ClassName SplitPackageClientHandler
 * @Description 客户端处理器
 * @Author zhangyuchenb
 * @Date 2023/4/24 16:03
 * @Version 1.0
 */
public class SplitPackageClientHandler extends SimpleChannelInboundHandler<SplitPackageProtocol> {

    //当通道有读取事件时会触发，即服务端发送数据给客户端
    @Override
    public void channelRead0(ChannelHandlerContext ctx, SplitPackageProtocol splitPackageProtocol) throws Exception {
//        ByteBuf buf = (ByteBuf) msg;
//        System.out.println(msg);
        System.out.println(splitPackageProtocol.getContent());
    }

}
