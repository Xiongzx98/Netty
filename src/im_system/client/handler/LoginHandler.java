package im_system.client.handler;

import im_system.data_packet.request.LoginRequestPacket;
import im_system.data_packet.response.LoginResponsePacket;
import im_system.data_packet.Packet;
import im_system.util.PacketCodecUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
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
        loginPacket.setUsername("xiong");
        loginPacket.setPassword("pwd");

        ByteBuf buffer = PacketCodecUtil.INSTANCE.encode(ctx.alloc(),loginPacket);

        buffer.skipBytes(7);
        System.out.println("length : " + buffer.readInt());

        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf responsePacket = (ByteBuf)msg;

        Packet packet = PacketCodecUtil.INSTANCE.decode(responsePacket);

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket loginResponsePacket = (LoginResponsePacket)packet;

            if(loginResponsePacket.isSuccess()){
                System.out.println(new Date() + ": " + "登录成功！");
            }else{
                System.out.println(loginResponsePacket.getReson());
            }

        }
    }
}
