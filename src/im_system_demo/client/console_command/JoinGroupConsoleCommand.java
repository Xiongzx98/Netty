package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
import im_system_demo.proto.request_packet.JoinGroupRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xiong
 * @date 2019-06-06  22:16
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print("加入群聊: ");
        String groupNickname = scanner.nextLine();

        JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();
        joinGroupRequestPacket.setGroupNickname(groupNickname);

        channel.writeAndFlush(joinGroupRequestPacket);
    }
}
