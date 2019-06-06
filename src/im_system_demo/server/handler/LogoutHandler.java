package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.LogoutRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xiong
 * @date 2019-06-06  21:24
 */
public class LogoutHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {

    }
}
