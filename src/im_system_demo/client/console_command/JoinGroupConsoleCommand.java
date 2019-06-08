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
        JoinGroupRequestPacket packet = new JoinGroupRequestPacket();

        System.out.print("加入的群聊名称: ");
        String nickname = scanner.nextLine();

        packet.setGroupNickname(nickname);
        channel.writeAndFlush(nickname);
    }
}
