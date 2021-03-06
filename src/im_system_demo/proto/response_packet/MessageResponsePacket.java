package im_system_demo.proto.response_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.MESSAGE_RESPONSE;

/**
 * @author xiong
 * @date 2019-05-31  21:49
 */
public class MessageResponsePacket extends Packet {

    private String fromUsername;
    private String message;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean flag) {
        this.success = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFromUsername() {
        return fromUsername;
    }

    public void setFromUsername(String fromUsername) {
        this.fromUsername = fromUsername;
    }

    @Override
    public Byte getCommand() {
        return MESSAGE_RESPONSE;
    }

}
