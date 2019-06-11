package im_system_demo.client.handler;

import im_system_demo.client.util.TimeUtil;
import im_system_demo.proto.response_packet.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;


/**
 * @author xiong
 * @date 2019-06-06  22:53
 */
public class JoinGroupHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket msg) throws Exception {
        if(msg.isSuccess()){
            System.out.println(TimeUtil.getTime() + " 您已成功加入群聊 " + msg.getGroupNickName());
        }else {
            System.out.println(TimeUtil.getTime() + " 加入群聊 " + msg.getGroupNickName() + " 失败,原因是: " + msg.getReason());
        }
    }
}
