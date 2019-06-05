package im_system.server.handler;

import im_system.proto.request_packet.LoginRequestPacket;
import im_system.proto.response_packet.LoginResponsePacket;
import im_system.server.session.Session;
import im_system.server.util.RedisUtil;
import im_system.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


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
        loginResponsePacket.setUsername(msg.getUsername());

        if (valid(msg)) {
            loginResponsePacket.setSuccess(true);
            System.out.println(new Date() + ": "+msg.getUsername()+"登陆成功!");
            SessionUtil.bindSession(new Session(msg.getUsername()), ctx.channel());
        } else {
            loginResponsePacket.setSuccess(false);
            loginResponsePacket.setReason("用户名和密码错误!");
            System.out.println(new Date() + ": 登陆失败!");
        }

        ctx.channel().writeAndFlush(loginResponsePacket);
    }

    private boolean valid(LoginRequestPacket loginRequestPacket) {
        boolean flag =  RedisUtil.loginValid(loginRequestPacket.getUsername(), loginRequestPacket.getPassword());
        return flag;
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        SessionUtil.unBindSession(ctx.channel());
    }
}
