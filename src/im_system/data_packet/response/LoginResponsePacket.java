package im_system.data_packet.response;

import im_system.data_packet.Packet;
import im_system.util.Impl.Command;

/**
 * @author xiong
 * @date 2019-05-29  10:33
 */
public class LoginResponsePacket extends Packet {

    private String reson;
    private boolean success;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
