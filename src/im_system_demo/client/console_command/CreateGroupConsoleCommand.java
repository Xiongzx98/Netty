package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
import im_system_demo.proto.request_packet.CreateGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author xiong
 * @date 2019-06-06  20:11
 */
public class CreateGroupConsoleCommand implements ConsoleCommand {

    private static final String USER_ID_SPLITER = ",";

    @Override
    public void exec(Scanner scanner, Channel channel) {
        CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

        System.out.print("[创建群聊] 请输入群昵称: ");
        String groupName = scanner.nextLine();
        System.out.print("[创建群聊] 输入群员username列表,username之间用“,”隔开:");
        String usersnameList = scanner.nextLine();
        createGroupRequestPacket.setNickname(groupName);
        createGroupRequestPacket.setUsernameList(Arrays.asList(usersnameList.replaceAll(" ","").split(",|，")));
        channel.writeAndFlush(createGroupRequestPacket);
    }
}
