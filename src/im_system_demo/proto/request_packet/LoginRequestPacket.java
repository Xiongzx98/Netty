package im_system_demo.proto.request_packet;

import im_system_demo.proto.Packet;
import im_system_demo.proto.impl.Command;

/**
 * @author xiong
 * @date 2019-05-28  14:53
 */

public class LoginRequestPacket extends Packet {

    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
