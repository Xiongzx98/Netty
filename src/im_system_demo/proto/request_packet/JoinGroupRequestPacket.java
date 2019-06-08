package im_system_demo.proto.request_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.JOIN_GROUP_REQUEST;

/**
 * @author xiong
 * @date 2019-06-06  22:42
 */
public class JoinGroupRequestPacket extends Packet {

    private String groupNickname;

    public String getGroupNicknamem() {
        return groupNickname;
    }

    public void setGroupNickname(String groupNickname) {
        this.groupNickname = groupNickname;
    }

    @Override
    public Byte getCommand() {
        return JOIN_GROUP_REQUEST;
    }
}
