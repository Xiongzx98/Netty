package im_system_demo.proto.response_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.LOGOUT_RESPONSE;

/**
 * @author xiong
 * @date 2019-06-06  20:33
 */
public class LogoutResponsePacket extends Packet {


    @Override
    public Byte getCommand() {
        return LOGOUT_RESPONSE;
    }
}
