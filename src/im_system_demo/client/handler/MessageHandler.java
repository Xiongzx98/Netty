package im_system_demo.client.handler;

import im_system_demo.client.util.TimeUtil;
import im_system_demo.proto.response_packet.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xiong
 * @date 2019-05-31  21:10
 */
public class MessageHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket msg) throws Exception {
        if(msg.isSuccess()) {
            System.out.println(TimeUtil.getTime() + " : " + msg.getMessage());
        } else{
            System.out.println(TimeUtil.getTime() + " : " + msg.getMessage());
        }
    }
}
