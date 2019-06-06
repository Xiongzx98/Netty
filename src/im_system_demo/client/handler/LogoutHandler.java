package im_system_demo.client.handler;

import im_system_demo.proto.response_packet.LogoutResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xiong
 * @date 2019-06-06  23:32
 */
public class LogoutHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket msg) throws Exception {

    }
}
