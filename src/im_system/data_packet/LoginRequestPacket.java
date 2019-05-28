package im_system.data_packet;

import im_system.util.Impl.Command;

/**
 * @author xiong
 * @date 2019-05-28  14:53
 */

public class LoginRequestPacket extends Packet {

    private Integer userID;
    private String username;
    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
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
