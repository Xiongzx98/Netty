package im_system.client.handler;

import im_system.proto.PacketCodec;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author xiong
 * @date 2019-06-02  10:10
 */
public class SplitHandler extends LengthFieldBasedFrameDecoder {

    private static final int FIELDOFFSET = 7;
    private static final int FIELDLENGTH = 4;

    public SplitHandler() {
        super(Integer.MAX_VALUE, FIELDOFFSET, FIELDLENGTH);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {

        if(in.readInt() != PacketCodec.MAGIC_NUMBER){
            return null;
        }

        return super.decode(ctx, in);
    }
}
