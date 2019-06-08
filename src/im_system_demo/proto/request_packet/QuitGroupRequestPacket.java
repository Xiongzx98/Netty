package im_system_demo.proto.request_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.QUIT_GROUP_REQUEST;

/**
 * @author xiong
 * @date 2019-06-06  23:42
 */
public class QuitGroupRequestPacket extends Packet {

    private String groupNickname;

    public String getGroupNickname() {
        return groupNickname;
    }

    public void setGroupNickname(String groupNickname) {
        this.groupNickname = groupNickname;
    }

    @Override
    public Byte getCommand() {
        return QUIT_GROUP_REQUEST;
    }
}
