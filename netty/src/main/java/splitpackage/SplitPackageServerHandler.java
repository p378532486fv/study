package splitpackage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Iterator;

/**
 * @ClassName SplitPackageServerHandler
 * @Description 服务端处理器
 * @Author zhangyuchenb
 * @Date 2023/4/24 16:02
 * @Version 1.0
 */
public class SplitPackageServerHandler extends SimpleChannelInboundHandler<SplitPackageProtocol> {

//    private static ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup("ChannelGroups", GlobalEventExecutor.INSTANCE);


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        Channel channel = ctx.channel();
        String clientName = channel.remoteAddress().toString();

        String msg = "你好客户端";
        SplitPackageProtocol splitPackageProtocol = new SplitPackageProtocol();
        splitPackageProtocol.setContent(msg);
        for(int i=1;i <= 100 ;i++){
        channel.writeAndFlush(splitPackageProtocol);
        }

        //服务器端提示XXX进入聊天室
//        System.out.println(clientName+"已进入聊天室");
        //广播到所有聊天室内的客户端
//        CHANNEL_GROUP.writeAndFlush(clientName+"已进入聊天室");
        //将这个客户端加入channel集
//        CHANNEL_GROUP.add(channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        Channel channel = ctx.channel();
        String clientName = channel.remoteAddress().toString();
        //服务器端提示XXX离开聊天室
        System.out.println(clientName+"离开聊天室");
        //把这个客户端从channel集中去除
//        CHANNEL_GROUP.remove(channel);
        //广播到所有聊天室内的客户端
//        CHANNEL_GROUP.writeAndFlush(clientName+"离开聊天室");
    }

    /**
     * 读取客户端发送的数据
     *
     * @param ctx 上下文对象, 含有通道channel，管道pipeline
     * @param obj 就是客户端发送的数据
     * @throws Exception
     */
    @Override
    public void channelRead0(ChannelHandlerContext ctx, SplitPackageProtocol obj) {
        Channel channel = ctx.channel();
        //服务器读取信息
        //将 msg 转成一个 ByteBuf，类似NIO 的 ByteBuffer
//        ByteBuf buf = (ByteBuf) msg;
        System.out.println(channel.remoteAddress().toString()+"：" + obj);

//        Iterator<Channel> iterator = CHANNEL_GROUP.iterator();
//        while(iterator.hasNext()){
//            Channel next = iterator.next();
//            if(channel != next){
//                next.writeAndFlush(channel.remoteAddress().toString()+"：" + msg);
//            }
//        }
    }


}
