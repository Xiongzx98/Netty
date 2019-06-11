package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
import im_system_demo.client.util.TimeUtil;
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
        while (!Thread.interrupted() && username != null) {
            System.out.print(TimeUtil.getTime() + " -> message to [" + username + "] : ");
            String message = scanner.nextLine();
            if(!message.equals("quit")) {
                MessageRequestPacket packet = new MessageRequestPacket(username, message);
                channel.writeAndFlush(packet);
            }else
                break;
        }

    }
}
