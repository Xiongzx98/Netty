package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.MessageRequestPacket;
import im_system_demo.proto.response_packet.MessageResponsePacket;
import im_system_demo.server.session.Session;
import im_system_demo.server.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author xiong
 * @date 2019-05-31  21:01
 */

@ChannelHandler.Sharable
public class MessageHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

    public static final MessageHandler INSTANCE = new MessageHandler();
    private MessageHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket msg) throws Exception {
        Session session = SessionUtil.getSession(ctx.channel());

        MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
        messageResponsePacket.setFromUsername(session.getUsername());

        Channel toChannel = SessionUtil.getChannel(msg.getToUsername());

        if (toChannel != null && SessionUtil.hasLogin(toChannel)) {
            messageResponsePacket.setSuccess(true);
            messageResponsePacket.setMessage(msg.getMessage());
            toChannel.writeAndFlush(messageResponsePacket);
        } else {
            messageResponsePacket.setSuccess(false);
            messageResponsePacket.setMessage("发送失败");
            System.err.println(new Date()+": [ " + msg.getToUsername() + " ] 不在线，发送失败!");
            toChannel.writeAndFlush(messageResponsePacket);
        }
    }

}
