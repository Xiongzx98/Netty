package im_system_demo.client.handler;

import im_system_demo.client.util.TimeUtil;
import im_system_demo.client.util.UserUtil;
import im_system_demo.proto.response_packet.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Date;

/**
 * @author xiong
 * @date 2019-06-06  23:44
 */
public class QuitGroupHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket msg) throws Exception {
        if(msg.isSuccess()){

            System.out.println(TimeUtil.getTime() + " 退出群聊 " + msg.getGroupNickname() +" 成功!");
        }else {
            System.out.println(TimeUtil.getTime() + " 退出失败: " + msg.getReason());
        }
    }
}
