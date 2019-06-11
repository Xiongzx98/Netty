package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.JoinGroupRequestPacket;
import im_system_demo.proto.response_packet.GroupMessageResponsePacket;
import im_system_demo.proto.response_packet.JoinGroupResponsePacket;
import im_system_demo.server.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author xiong
 * @date 2019-06-06  22:53
 */
public class JoinGroupHandler extends SimpleChannelInboundHandler<JoinGroupRequestPacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupRequestPacket msg) throws Exception {
        String nickname = msg.getGroupNickname();
        ChannelGroup group = SessionUtil.getChannelGroup(nickname);

        if(group != null) {
            GroupMessageResponsePacket groupPacket = new GroupMessageResponsePacket();
            group.add(ctx.channel());
            groupPacket.setSuccess(true);

            groupPacket.setMessage("欢迎 <"+ SessionUtil.getSession(ctx.channel()).getUsername() +"> 加入 ["+ msg.getGroupNickname() +"]");
            group.writeAndFlush(groupPacket);
        } else {
            JoinGroupResponsePacket joinGroupResponsePacket = new JoinGroupResponsePacket();
            joinGroupResponsePacket.setSuccess(false);
            joinGroupResponsePacket.setMessage("未创建此群组");
            ctx.channel().writeAndFlush(joinGroupResponsePacket);
        }
    }
}
