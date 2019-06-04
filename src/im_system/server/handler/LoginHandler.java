package im_system.server.handler;

import im_system.proto.request_packet.LoginRequestPacket;
import im_system.proto.response_packet.LoginResponsePacket;
import im_system.server.redis.RedisPoll;
import im_system.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import redis.clients.jedis.Jedis;

import java.util.Date;

/**
 * @author xiong
 * @date 2019-05-31  17:14
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
        LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
        loginResponsePacket.setVersion(msg.getVersion());

        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": 登陆成功!");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名和密码错误!");
            System.out.println(new Date() + ": 登陆失败!");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }



    private boolean valid(LoginRequestPacket loginRequestPacket) {
        boolean flag = false;
        String pwd = null;
        Jedis jedis = null;
        try {
            jedis = RedisPoll.getJedis();
            jedis.connect();
            pwd = jedis.get(loginRequestPacket.getUsername());
        }catch (Exception e){
            RedisPoll.returnBrokerQueue(jedis);
        }finally {
            if (pwd != null && pwd.equals(loginRequestPacket.getPassword()))
                flag = true;
            RedisPoll.returnJedis(jedis);
        }

        return flag;
    }
}
