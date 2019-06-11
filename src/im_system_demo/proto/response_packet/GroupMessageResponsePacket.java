package im_system_demo.proto.response_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.GROUP_MESSAGE_RESPONSE;

/**
 * @author xiong
 * @date 2019-06-10  09:57
 */
public class GroupMessageResponsePacket extends Packet {

    private String fromUsername;
    private String groupNickename;
    private String message;
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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

    public String getGroupNickename() {
        return groupNickename;
    }

    public void setGroupNickename(String groupNickename) {
        this.groupNickename = groupNickename;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_RESPONSE;
    }
}
