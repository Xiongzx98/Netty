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

    private String toUsername;
    private String message;

    public MessageRequestPacket(String toUsername, String message){
        this.toUsername = toUsername;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getToUsername() {
        return toUsername;
    }

}
