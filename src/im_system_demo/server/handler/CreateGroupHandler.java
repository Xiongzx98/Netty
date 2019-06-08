package im_system_demo.server.handler;

import im_system_demo.proto.request_packet.CreateGroupRequestPacket;
import im_system_demo.proto.response_packet.CreateGroupResponsePacket;
import im_system_demo.server.util.IDUtil;
import im_system_demo.server.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiong
 * @date 2019-06-06  21:24
 */
public class CreateGroupHandler extends SimpleChannelInboundHandler<CreateGroupRequestPacket> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, CreateGroupRequestPacket msg) throws Exception {
        List<String> usernameList = msg.getUsernameList();
        String nickname = msg.getNickname();

        List<String> users = new ArrayList<>();

        ChannelGroup group = new DefaultChannelGroup(ctx.executor());

        for(String name : usernameList){
            Channel channel = SessionUtil.getChannel(name);
            if(channel != null){
                group.add(channel);
                users.add(name);
            }
        }

        CreateGroupResponsePacket groupPacket = new CreateGroupResponsePacket();
        groupPacket.setSuccess(true);
        groupPacket.setUUID(IDUtil.getID());
        groupPacket.setNickname(nickname);
        groupPacket.setUsers(users);

        group.writeAndFlush(groupPacket);
        System.out.println("创群成功: " + groupPacket.getNickname());
        System.out.println("群成员: " + groupPacket.getUsers());

    }
}
