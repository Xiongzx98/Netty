package im_system_demo.client.handler;

import im_system_demo.proto.request_packet.HeartBeatRequestPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.concurrent.TimeUnit;

/**
 * @author xiong
 * @date 2019-06-12  14:13
 */
public class HeartBeatHandler extends ChannelInboundHandlerAdapter {

    private static final int HEART_BEAT_TIME = 5;


    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        scheduleSendHeartBeat(ctx);

        super.channelActive(ctx);
    }

    private void scheduleSendHeartBeat(ChannelHandlerContext ctx){

        ctx.executor().schedule(()->{

            if(ctx.channel().isActive()){
                ctx.writeAndFlush(new HeartBeatRequestPacket());
                scheduleSendHeartBeat(ctx);
            }

        }, HEART_BEAT_TIME, TimeUnit.SECONDS);

    }

}
