package im_system_demo.proto.response_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.CRETE_GROUP_RESPONSE;

/**
 * @author xiong
 * @date 2019-06-06  20:26
 */
public class CreateGroupResponsePacket extends Packet {

    @Override
    public Byte getCommand() {
        return CRETE_GROUP_RESPONSE;
    }
}
