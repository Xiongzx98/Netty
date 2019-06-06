package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.CreateGroupRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xiong
 * @date 2019-06-06  21:24
 */
public class CreateGroupHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {

    }
}
