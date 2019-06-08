package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.QuitGroupRequestPacket;
import im_system_demo.proto.response_packet.QuitGroupResponsePacket;
import im_system_demo.server.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author xiong
 * @date 2019-06-06  23:45
 */
public class QuitGroupHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket msg) throws Exception {
        String nickname = msg.getGroupNickname();
        ChannelGroup group = SessionUtil.getChannelGroup(nickname);
        QuitGroupResponsePacket packet = new QuitGroupResponsePacket();

        if(group != null){
            group.remove(ctx.channel());
            packet.setSuccess(true);
            packet.setGroupNickname(nickname);
        }else {
            packet.setSuccess(false);
            packet.setGroupNickname(nickname);
            packet.setReason("未创建此群聊!");
        }

        ctx.channel().writeAndFlush(packet);

    }
}
