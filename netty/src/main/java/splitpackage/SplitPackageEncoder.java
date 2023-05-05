package splitpackage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @ClassName SplitPackageEncoder
 * @Description 自定义编码器
 * @Author zhangyuchenb
 * @Date 2023/5/5 15:44
 * @Version 1.0
 */
public class SplitPackageEncoder extends MessageToByteEncoder<SplitPackageProtocol> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, SplitPackageProtocol splitPackageProtocol, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(splitPackageProtocol.getLength());
        byteBuf.writeBytes(splitPackageProtocol.getContent().getBytes("UTF-8"));
    }
}
