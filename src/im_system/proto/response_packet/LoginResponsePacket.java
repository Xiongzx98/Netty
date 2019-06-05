package im_system.proto.response_packet;

import im_system.proto.Packet;

import static im_system.proto.impl.Command.LOGIN_RESPONSE;

/**
 * @author xiong
 * @date 2019-05-29  10:33
 */
public class LoginResponsePacket extends Packet {

    private String username;
    private boolean success;
    private String reason;

    @Override
    public Byte getCommand() {
        return LOGIN_RESPONSE;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
