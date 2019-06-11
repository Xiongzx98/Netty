package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
import im_system_demo.proto.request_packet.ShowGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xiong
 * @date 2019-06-06  22:55
 */
public class ShowGroupMembersConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("查看群聊: ");
        String groupNickname = scanner.nextLine();

        ShowGroupRequestPacket showGroupRequestPacket = new ShowGroupRequestPacket();
        showGroupRequestPacket.setGroupNickname(groupNickname);

        channel.writeAndFlush(showGroupRequestPacket);
    }
}
