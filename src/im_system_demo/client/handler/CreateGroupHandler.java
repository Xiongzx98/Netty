package im_system_demo.client.handler;

import im_system_demo.proto.response_packet.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xiong
 * @date 2019-06-06  23:31
 */
public class CreateGroupHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket msg) throws Exception {
        System.out.println("您已成功加入: " + msg.getNickname() + " 群聊!");
        System.out.println("和您一同在群里的有: " + msg.getUsers());
    }
}
