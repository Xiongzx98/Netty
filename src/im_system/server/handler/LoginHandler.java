package im_system.server.handler;

import com.alibaba.fastjson.JSON;
import im_system.data_packet.Packet;
import im_system.data_packet.request.LoginRequestPacket;
import im_system.data_packet.response.LoginResponsePacket;
import im_system.util.PacketCodecUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import redis.clients.jedis.Jedis;


/**
 * @author xiong
 * @date 2019-05-28  18:09
 */
public class LoginHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf requestBytebuf = (ByteBuf)msg;

        Packet packet = PacketCodecUtil.INSTANCE.decode(requestBytebuf);

        if(packet instanceof LoginRequestPacket){
            LoginRequestPacket loginRequestPacket = (LoginRequestPacket)packet;
            LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
            loginResponsePacket.setVersion(packet.getVersion());

            if(valid(loginRequestPacket)){
                loginResponsePacket.setSuccess(true);
                System.out.println("登陆成功!");
            }else {
                loginResponsePacket.setSuccess(false);
                loginResponsePacket.setReason("用户名和密码错误!");
            }

            ByteBuf buffer = PacketCodecUtil.INSTANCE.encode(ctx.alloc(), loginResponsePacket);

            buffer.skipBytes(4);
            buffer.skipBytes(1);
            byte serializeAlgorithm = buffer.readByte();
            byte command = buffer.readByte();
            int length = buffer.readInt();
//            byte[] bytes = new byte[length];
//            buffer.readBytes(bytes);

            System.out.println(serializeAlgorithm+ " " + command + " " + length);

            ctx.channel().writeAndFlush(buffer);
        }
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        boolean flag = false;
        Jedis jedis = new Jedis("localhost", 6379);
        jedis.connect();
        String pwd = jedis.get(loginRequestPacket.getUsername());
        if(pwd != null && pwd.equals(loginRequestPacket.getPassword()))
            flag = true;
        jedis.disconnect();
        return flag;
    }

}
