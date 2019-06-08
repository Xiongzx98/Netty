package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
import im_system_demo.proto.request_packet.MessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xiong
 * @date 2019-06-06  20:10
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {

        System.out.print("接收者: ");
        String username = scanner.nextLine();
        System.out.print("发送信息: ");
        String message = scanner.nextLine();

        MessageRequestPacket packet = new MessageRequestPacket(username, message);

        channel.writeAndFlush(packet);

    }
}
