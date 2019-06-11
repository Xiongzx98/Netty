package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.ShowGroupRequestPacket;
import im_system_demo.proto.response_packet.ShowGroupResponsePacket;
import im_system_demo.server.session.Session;
import im_system_demo.server.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiong
 * @date 2019-06-06  22:54
 */
public class ShowGroupMembersHandler extends SimpleChannelInboundHandler<ShowGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ShowGroupRequestPacket msg) throws Exception {
        String nickname = msg.getGroupNickname();
        ChannelGroup group = SessionUtil.getChannelGroup(nickname);
        ShowGroupResponsePacket packet = new ShowGroupResponsePacket();

        if(group != null) {
            List<Session> members = new ArrayList<>();
            for (Channel channel : group) {
                Session session = SessionUtil.getSession(channel);
                members.add(session);
            }
            packet.setSuccess(true);
            packet.setNickname(nickname);
            packet.setMembers(members);
        }else {
            packet.setNickname(nickname);
            packet.setSuccess(false);
            packet.setReason("未创建此群聊");
        }

        ctx.channel().writeAndFlush(packet);
    }
}
