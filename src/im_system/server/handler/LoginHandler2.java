package im_system.server.handler;

import im_system.proto.Packet;
import im_system.proto.PacketCodec;
import im_system.proto.request_packet.LoginRequestPacket;
import im_system.proto.request_packet.MessageRequestPacket;
import im_system.proto.response_packet.LoginResponsePacket;
import im_system.proto.response_packet.MessageResponsePacket;
import im_system.redis.RedisPoll;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import redis.clients.jedis.Jedis;

import java.util.Date;


/**
 * @author xiong
 * @date 2019-05-28  18:09
 */
public class LoginHandler2 extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg){
        ByteBuf requestBytebuf = (ByteBuf)msg;

        Packet packet = PacketCodec.INSTANCE.decode(requestBytebuf);

        ByteBuf buf = ctx.alloc().buffer();
        if(packet instanceof LoginRequestPacket) {
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket) packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());

            if (valid(loginRequestPacket)) {
                loginResponsePacket.setSuccess(true);
                System.out.println(new Date() + " 登陆成功!");
            } else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("用户名和密码错误!");
                System.out.println("登录失败!");
            }

            PacketCodec.INSTANCE.encode(buf, loginResponsePacket);
            ctx.channel().writeAndFlush(buf);
        }else if(packet instanceof MessageRequestPacket){
            MessageRequestPacket messageRequest = (MessageRequestPacket)packet;
            System.out.println(new Date() + " 服务端收到消息: "+messageRequest.getMessage());

            MessageResponsePacket messageResponse = new MessageResponsePacket();
            messageResponse.setMessage("服务端回复： " + "lalala...");
            PacketCodec.INSTANCE.encode(buf, messageResponse);
            ctx.channel().writeAndFlush(buf);
        }

    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        boolean flag = false;
        Jedis jedis = RedisPoll.getJedis();
        jedis.connect();
        String pwd = jedis.get(loginRequestPacket.getUsername());
        if(pwd != null && pwd.equals(loginRequestPacket.getPassword()))
            flag = true;
        RedisPoll.returnJedis(jedis);
        return flag;
    }

}
