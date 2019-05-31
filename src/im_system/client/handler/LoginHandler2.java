package im_system.client.handler;

import im_system.proto.PacketCodec;
import im_system.proto.request_packet.LoginRequestPacket;
import im_system.proto.response_packet.LoginResponsePacket;
import im_system.proto.Packet;
import im_system.proto.response_packet.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Date;
import java.util.UUID;

/**
 * @author xiong
 * @date 2019-05-28  18:14
 */
public class LoginHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx){

        LoginRequestPacket loginPacket = new LoginRequestPacket();
        loginPacket.setUserID(UUID.randomUUID().toString());
        loginPacket.setUsername("ironman");
        loginPacket.setPassword("loveu3k");

        ByteBuf buffer = ctx.alloc().ioBuffer();
        PacketCodec.INSTANCE.encode(buffer,loginPacket);
        ctx.channel().writeAndFlush(buffer);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf responseByteBuf = (ByteBuf)msg;

        Packet packet = PacketCodec.INSTANCE.decode(responseByteBuf);

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket responsePacket = (LoginResponsePacket)packet;

            if(responsePacket.isSuccess()){

                System.out.println(new Date() + ": " + "登录成功！");
            }else{
                System.out.println(responsePacket.getReason());
            }
        }else if (packet instanceof MessageResponsePacket) {
            MessageResponsePacket messageResponsePacket = (MessageResponsePacket) packet;
            System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
        }
    }
}
