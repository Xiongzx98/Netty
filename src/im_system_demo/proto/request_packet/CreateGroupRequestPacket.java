package im_system_demo.proto.request_packet;

import im_system_demo.proto.Packet;

import java.util.List;

import static im_system_demo.proto.impl.Command.CRETE_GROUP_REQUEST;

/**
 * @author xiong
 * @date 2019-06-06  20:26
 */
public class CreateGroupRequestPacket extends Packet {

    private String nickname;
    private List<String> usernameList;

    public List<String> getUsernameList() {
        return usernameList;
    }

    public void setUsernameList(List<String> usernameList) {
        this.usernameList = usernameList;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public Byte getCommand() {
        return CRETE_GROUP_REQUEST;
    }
}
