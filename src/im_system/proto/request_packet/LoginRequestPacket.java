package im_system.proto.request_packet;

import im_system.proto.Packet;
import im_system.proto.impl.Command;

/**
 * @author xiong
 * @date 2019-05-28  14:53
 */

public class LoginRequestPacket extends Packet {

    private String userID;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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
