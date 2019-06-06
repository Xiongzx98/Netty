package im_system_demo.proto.request_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.LOGOUT_REQUEST;

/**
 * @author xiong
 * @date 2019-06-06  20:33
 */
public class LogoutRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return LOGOUT_REQUEST;
    }
}
