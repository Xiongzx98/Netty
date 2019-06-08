package im_system_demo.proto.response_packet;

import im_system_demo.proto.Packet;

import java.util.List;

import static im_system_demo.proto.impl.Command.CRETE_GROUP_RESPONSE;

/**
 * @author xiong
 * @date 2019-06-06  20:26
 */
public class CreateGroupResponsePacket extends Packet {

    private boolean success;
    private String uuid;
    private List<String> users;
    private String nickname;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

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

    public String getUUID() {
        return uuid;
    }

    public void setUUID(String uuid) {
        this.uuid = uuid;
    }

    public List<String> getUsers() {
        return users;
    }

    public void setUsers(List<String> users) {
        this.users = users;
    }

    @Override
    public Byte getCommand() {
        return CRETE_GROUP_RESPONSE;
    }
}
