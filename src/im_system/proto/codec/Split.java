package im_system.proto.codec;

import im_system.proto.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author xiong
 * @date 2019-06-03  10:28
 */
public class Split extends LengthFieldBasedFrameDecoder {

    private static final int FIELDOFFSET = 7;
    private static final int FIELDLENGTH = 4;

    public Split() {
        super(Integer.MAX_VALUE, FIELDOFFSET, FIELDLENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        if(in.getInt(in.readerIndex()) != PacketCodec.MAGIC_NUMBER){
            ctx.channel().close();
            return null;
        }

        return super.decode(ctx, in);
    }
}
