package splitpackage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @ClassName SplitPackageDecoder
 * @Description 自定义解码器
 * @Author zhangyuchenb
 * @Date 2023/5/5 15:44
 * @Version 1.0
 */
public class SplitPackageDecoder extends ByteToMessageDecoder {

    int length = 0;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {

        //如果可读的字节数大于4,则说明长度已经读过来了
        if(byteBuf.readableBytes() >= 4){
            if(length == 0)
                length = byteBuf.readInt();

            if(byteBuf.readableBytes() < length){
                //未读全,继续等待
                return;
            }
            byte[] bytes = new byte[length];

            if(byteBuf.readableBytes() >= length){
                byteBuf.readBytes(bytes);
                SplitPackageProtocol splitPackageProtocol = new SplitPackageProtocol();
                splitPackageProtocol.setContent(new String(bytes,"UTF-8"));
                list.add(splitPackageProtocol);
            }
        }

        length = 0;
    }
}
