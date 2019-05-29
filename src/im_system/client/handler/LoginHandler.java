package im_system.client.handler;

import im_system.data_packet.LoginRequestPacket;
import im_system.util.PacketCodecUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

/**
 * @author xiong
 * @date 2019-05-28  18:14
 */
public class LoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LoginRequestPacket loginPacket = new LoginRequestPacket();
        loginPacket.setUserID(UUID.randomUUID().toString());
        loginPacket.setUsername("IronMan".toLowerCase());
        loginPacket.setPassword("LoveU3000K".toLowerCase());

        ByteBuf buffer = PacketCodecUtil.INSTANCE.encode(ctx.alloc(),loginPacket);

        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }
}
