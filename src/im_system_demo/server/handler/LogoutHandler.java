package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.LogoutRequestPacket;
import im_system_demo.proto.response_packet.LogoutResponsePacket;
import im_system_demo.server.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xiong
 * @date 2019-06-06  21:24
 */
@ChannelHandler.Sharable
public class LogoutHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

    public static final LogoutHandler INSTANCE = new LogoutHandler();
    private LogoutHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) throws Exception {
        SessionUtil.unBindSession(ctx.channel());
        LogoutResponsePacket packet = new LogoutResponsePacket();
    }
}
