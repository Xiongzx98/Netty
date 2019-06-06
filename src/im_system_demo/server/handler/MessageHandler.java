package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.MessageRequestPacket;
import im_system_demo.proto.response_packet.MessageResponsePacket;
import im_system_demo.server.session.Session;
import im_system_demo.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author xiong
 * @date 2019-05-31  21:01
 */
public class MessageHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());
        System.out.println(session.getUsername());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUsername(session.getUsername());
//        System.out.println(new Date() + ": 收到[ "+ session.getUsername() +" ]消息: " + msg.getMessage());
        messageResponsePacket.setMessage(msg.getMessage());

        Channel toChannel = SessionUtil.getChannel(msg.getToUsername());

        if (toChannel != null && SessionUtil.hasLogin(toChannel)) {
            toChannel.writeAndFlush(messageResponsePacket);
        } else {
            System.err.println(new Date()+": [ " + msg.getToUsername() + " ] 不在线，发送失败!");
        }
    }


}
