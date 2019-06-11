package im_system_demo.proto.request_packet;

import im_system_demo.proto.Packet;

import static im_system_demo.proto.impl.Command.GROUP_MESSAGE_REQUEST;

/**
 * @author xiong
 * @date 2019-06-10  09:54
 */
public class GroupMessageRequestPacket extends Packet {

    private String groupNickname;
    private String message;

    public GroupMessageRequestPacket(String nickname, String message){
        this.groupNickname = nickname;
        this.message = message;
    }
    public GroupMessageRequestPacket(){}

    public String getGroupNickname() {
        return groupNickname;
    }

    public void setGroupNickname(String groupNickname) {
        this.groupNickname = groupNickname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public Byte getCommand() {
        return GROUP_MESSAGE_REQUEST;
    }
}
