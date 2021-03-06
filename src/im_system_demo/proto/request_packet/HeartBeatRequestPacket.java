package im_system_demo.proto.request_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.HEART_BEAT_REQUEST;

/**
 * @author xiong
 * @date 2019-06-12  14:11
 */
public class HeartBeatRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return HEART_BEAT_REQUEST;
    }
}
