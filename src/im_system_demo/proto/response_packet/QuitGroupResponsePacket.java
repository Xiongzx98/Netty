package im_system_demo.proto.response_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.QUIT_GROUP_RESPONSE;

/**
 * @author xiong
 * @date 2019-06-06  23:43
 */
public class QuitGroupResponsePacket extends Packet {

    private String groupId;
    private String groupNickname;
    private boolean success;
    private String reason;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupNickname() {
        return groupNickname;
    }

    public void setGroupNickname(String groupNickname) {
        this.groupNickname = groupNickname;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_RESPONSE;
    }
}
