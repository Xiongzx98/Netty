package im_system.data_packet.response;

import im_system.data_packet.Packet;

import static im_system.util.Impl.Command.LOGIN_RESPONSE;

/**
 * @author xiong
 * @date 2019-05-29  10:33
 */
public class LoginResponsePacket extends Packet {

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
}
