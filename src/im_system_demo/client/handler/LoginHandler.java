package im_system_demo.client.handler;

import im_system_demo.proto.response_packet.LoginResponsePacket;
import im_system_demo.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author xiong
 * @date 2019-05-31  21:21
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket msg) throws Exception {
        String userName = msg.getUsername();

        if (msg.isSuccess()) {
            System.out.println(new Date() + " [" + userName + "] 登录成功");
            LoginUtil.markAsLogin(ctx.channel());
        } else {
            System.out.println(new Date() + "[" + userName + "] 登录失败，原因：" + msg.getReason());
        }
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("客户端连接被关闭!");
    }



}
