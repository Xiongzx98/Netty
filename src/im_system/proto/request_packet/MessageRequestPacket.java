package im_system.proto.request_packet;

import im_system.proto.Packet;

import static im_system.proto.impl.Command.MESSAGE_REQUEST;

/**
 * @author xiong
 * @date 2019-05-31  11:43
 */
public class MessageRequestPacket extends Packet {
    @Override
    public Byte getCommand() {
        return MESSAGE_REQUEST;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
