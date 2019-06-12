package im_system_demo.server.handler;

import im_system_demo.proto.Packet;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.HashMap;
import java.util.Map;

import static im_system_demo.proto.impl.Command.*;

/**
 * @author xiong
 * @date 2019-06-12  09:43
 */
@ChannelHandler.Sharable
public class IMHandler extends SimpleChannelInboundHandler<Packet>{

    public static final IMHandler INSTANCE = new IMHandler();

    private Map<Byte, SimpleChannelInboundHandler<? extends Packet>> handlerMap;

    private IMHandler(){
        this.handlerMap = new HashMap<>();

        handlerMap.put(MESSAGE_REQUEST, MessageHandler.INSTANCE);
        handlerMap.put(CRETE_GROUP_REQUEST, CreateGroupHandler.INSTANCE);
        handlerMap.put(JOIN_GROUP_REQUEST, JoinGroupHandler.INSTANCE);
        handlerMap.put(QUIT_GROUP_REQUEST, QuitGroupHandler.INSTANCE);
        handlerMap.put(SHOW_GROUP_MEMBERS_REQUEST, ShowGroupMembersHandler.INSTANCE);
        handlerMap.put(GROUP_MESSAGE_REQUEST, GroupMessageHandler.INSTANCE);
        handlerMap.put(LOGOUT_REQUEST, LogoutHandler.INSTANCE);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        handlerMap.get(msg.getCommand()).channelRead(ctx, msg);
    }


}
