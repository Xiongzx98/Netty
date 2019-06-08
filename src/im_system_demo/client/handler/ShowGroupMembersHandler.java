package im_system_demo.client.handler;

import im_system_demo.proto.response_packet.ShowGroupResponsePacket;
import im_system_demo.server.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiong
 * @date 2019-06-06  22:57
 */
public class ShowGroupMembersHandler extends SimpleChannelInboundHandler<ShowGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ShowGroupResponsePacket msg) throws Exception {
        if (msg.isSuccess()){
            List<Session> members = msg.getMembers();
            List<String> users = new ArrayList<>();
            for (Session session : members){
                users.add(session.getUsername());
            }
            System.out.println(new Date() + " 群聊 " + msg.getNickname() + " 成员有: " + users);
        }else {
            System.out.println(new Date() + " " + msg.getNickname() + " 查询失败: " + msg.getReason());
        }
    }
}
