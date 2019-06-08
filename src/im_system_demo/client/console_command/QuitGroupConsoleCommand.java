package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
import im_system_demo.proto.request_packet.QuitGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xiong
 * @date 2019-06-06  23:44
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        QuitGroupRequestPacket  packet = new QuitGroupRequestPacket();

        System.out.print("退出群聊名称: ");
        String groupNickname = scanner.nextLine();
        packet.setGroupNickname(groupNickname);

        channel.writeAndFlush(packet);
    }
}
