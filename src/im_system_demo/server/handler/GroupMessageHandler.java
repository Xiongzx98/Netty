package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.GroupMessageRequestPacket;
import im_system_demo.proto.response_packet.GroupMessageResponsePacket;
import im_system_demo.server.util.SessionUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author xiong
 * @date 2019-06-11  13:35
 */

@ChannelHandler.Sharable
public class GroupMessageHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {

    public static final GroupMessageHandler INSTANCE = new GroupMessageHandler();
    private GroupMessageHandler(){}

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket msg) throws Exception {

        String nickname = msg.getGroupNickname();
        ChannelGroup  group = SessionUtil.getChannelGroup(nickname);
        GroupMessageResponsePacket packet = new GroupMessageResponsePacket();

        if(group != null){
            packet.setFromUsername(SessionUtil.getSession(ctx.channel()).getUsername());
            packet.setGroupNickename(nickname);
            packet.setMessage( " ["+ msg.getGroupNickname() + ":" + packet.getFromUsername() +"]-> " + msg.getMessage());
            packet.setSuccess(true);
            group.add(ctx.channel());
            group.writeAndFlush(packet);
        }else {
            packet.setSuccess(false);
            packet.setMessage("未创建此群组!");
            ctx.writeAndFlush(packet);
        }

    }
}
