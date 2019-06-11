package im_system_demo.client.handler;

import im_system_demo.client.util.TimeUtil;
import im_system_demo.proto.response_packet.GroupMessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author xiong
 * @date 2019-06-11  13:35
 */
public class GroupMessageHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket msg) throws Exception {
        if(msg.isSuccess()){
            System.out.println(TimeUtil.getTime() + " 您接收到来自群聊 [" + msg.getGroupNickename() +"] 的 ["+msg.getFromUsername()+"] 消息: " + msg.getMessage());
        }else
            System.out.println(TimeUtil.getTime() + "消息发送失败! " + msg.getMessage());
    }
}
