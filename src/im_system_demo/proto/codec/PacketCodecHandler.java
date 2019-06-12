package im_system_demo.proto.codec;

import im_system_demo.proto.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;

import java.util.List;

/**
 * @author xiong
 * @date 2019-06-12  09:44
 */

@ChannelHandler.Sharable
public class PacketCodecHandler extends MessageToMessageCodec {

    public static final PacketCodecHandler INSTANCE = new PacketCodecHandler();
    private PacketCodecHandler(){}

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        ByteBuf byteBuf = ctx.channel().alloc().ioBuffer();
        PacketCodec.INSTANCE.encode(byteBuf, (Packet) msg);
        out.add(byteBuf);
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, Object msg, List out) throws Exception {
        out.add(PacketCodec.INSTANCE.decode((ByteBuf)msg));
    }
}
