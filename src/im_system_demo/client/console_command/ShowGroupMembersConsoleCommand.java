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
        ShowGroupRequestPacket packet = new ShowGroupRequestPacket();

        System.out.print("查看群聊成员: ");
        String nickname = scanner.nextLine();

        packet.setGroupNickname(nickname);
        channel.writeAndFlush(nickname);
    }
}
