package im_system_demo.proto.response_packet;

import im_system_demo.proto.Packet;
import im_system_demo.server.session.Session;

import java.util.List;

import static im_system_demo.proto.impl.Command.SHOW_GROUP_MEMBERS_RESPONSE;

/**
 * @author xiong
 * @date 2019-06-06  22:46
 */
public class ShowGroupResponsePacket extends Packet {

    private String nickname;
    private boolean success;
    private List<Session> members;
    private String reason;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Session> getMembers() {
        return members;
    }

    public void setMembers(List<Session> members) {
        this.members = members;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public Byte getCommand() {
        return SHOW_GROUP_MEMBERS_RESPONSE;
    }
}
