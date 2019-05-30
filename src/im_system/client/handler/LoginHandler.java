package im_system.client.handler;

import com.alibaba.fastjson.JSON;
import im_system.data_packet.request.LoginRequestPacket;
import im_system.data_packet.response.LoginResponsePacket;
import im_system.data_packet.Packet;
import im_system.util.PacketCodecUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.UUID;

/**
 * @author xiong
 * @date 2019-05-28  18:14
 */
public class LoginHandler extends ChannelInboundHandlerAdapter {

//    @Override
//    public void channelActive(ChannelHandlerContext ctx) throws Exception {
//
//        LoginRequestPacket loginPacket = new LoginRequestPacket();
//        loginPacket.setUserID(UUID.randomUUID().toString());
//        loginPacket.setUsername("xiong");
//        loginPacket.setPassword("pwd");
//
//        ByteBuf buffer = PacketCodecUtil.INSTANCE.encode(ctx.alloc(),loginPacket);
//
//        System.out.println(buffer.readInt());
//
//        ctx.channel().writeAndFlush(buffer);
//    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception{
//
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
        loginRequestPacket.setUserID(UUID.randomUUID().toString());
        loginRequestPacket.setUsername("ironman");
        loginRequestPacket.setPassword("loveu3000k");

        byte[] bytes = JSON.toJSONBytes(loginRequestPacket);

        ByteBuf buf = ctx.alloc().ioBuffer();

        buf.writeInt(0x76737865);
        buf.writeByte(loginRequestPacket.getVersion());
        buf.writeByte(1);
        buf.writeByte(loginRequestPacket.getCommand());
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        ctx.channel().writeAndFlush(buf);

    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf responseByteBuf = (ByteBuf)msg;

//        Packet packet = PacketCodecUtil.INSTANCE.decode(responseByteBuf);

        responseByteBuf.skipBytes(4);
        responseByteBuf.skipBytes(1);
        byte serializeAlgorithm = responseByteBuf.readByte();
        byte command = responseByteBuf.readByte();
        int length = responseByteBuf.readInt();
        byte[] bytes = new byte[length];
        responseByteBuf.readBytes(bytes);

        Packet packet = JSON.parseObject(bytes, LoginResponsePacket.class);

        if(packet instanceof LoginResponsePacket){
            LoginResponsePacket responsePacket = (LoginResponsePacket)packet;

            if(responsePacket.isSuccess()){
                System.out.println(new Date() + ": " + "登录成功！");
            }else{
                System.out.println(responsePacket.getReason());
            }

        }
    }
}
