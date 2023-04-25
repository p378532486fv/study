package test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

/**
 * @ClassName TestByteBuf
 * @Description ByteBuf类实操
 * @Author zhangyuchenb
 * @Date 2023/4/24 15:38
 * @Version 1.0
 */
public class TestByteBuf {
    public static void main(String[] args) {
        //创建固定长度
        ByteBuf byteBuf = Unpooled.buffer(10);

        System.out.println("当前可写入的字节数量："+byteBuf.writableBytes());
        System.out.println("当前可读出的字节数量："+byteBuf.readableBytes());

        for(int i = 1;i <= 5;i++){
            byteBuf.writeByte(i);
        }

        System.out.println("当前可写入的字节数量："+byteBuf.writableBytes());
        System.out.println("当前可读出的字节数量："+byteBuf.readableBytes());

        for(int i = 1;i <= 5;i++){
            System.out.println(byteBuf.getByte(i));
        }

        System.out.println("当前可写入的字节数量："+byteBuf.writableBytes());
        System.out.println("当前可读出的字节数量："+byteBuf.readableBytes());

        for(int i = 1;i <= 3;i++){
            System.out.println(byteBuf.readByte());
        }

        System.out.println("当前可写入的字节数量："+byteBuf.writableBytes());
        System.out.println("当前可读出的字节数量："+byteBuf.readableBytes());


        //长度超出情况
        ByteBuf byteBuf2 = Unpooled.buffer(5);
        for(int i = 1;i <= 6;i++){
            byteBuf2.writeByte(i);
        }
        System.out.println("当前可写入的字节数量："+byteBuf2.writableBytes());
        System.out.println("当前可读出的字节数量："+byteBuf2.readableBytes());
        for(int i = 1;i <= 3;i++){
            System.out.println(byteBuf2.readByte());
        }

        System.out.println("当前可写入的字节数量："+byteBuf2.writableBytes());
        System.out.println("当前可读出的字节数量："+byteBuf2.readableBytes());

        //字符串转成字节数组
        ByteBuf byteBuf3 = Unpooled.copiedBuffer("hello,chen!", CharsetUtil.UTF_8);
        if(byteBuf3.hasArray()){
            byte[] array = byteBuf3.array();
            System.out.println("第一个字节信息："+array[0]);
            System.out.println("当前可写入的字节数量："+byteBuf3.writableBytes());
        }
    }
}
