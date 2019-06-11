package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
import im_system_demo.client.util.TimeUtil;
import im_system_demo.proto.request_packet.GroupMessageRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xiong
 * @date 2019-06-10  10:04
 */
public class SendToGroupMessageConsoleCommand implements ConsoleCommand {
    @Override
    public void exec(Scanner scanner, Channel channel) {
        System.out.print(TimeUtil.getTime() + "发送至群聊: ");
        String groupNickname = scanner.nextLine();
        GroupMessageRequestPacket groupMessageRequestPacket = new GroupMessageRequestPacket();

        while (!Thread.interrupted() && groupNickname != null){
            System.out.print(TimeUtil.getTime() + " -> ["+ groupNickname +"]: ");
            String message = scanner.nextLine();
            if(message.equals("quit group"))
                break;
            groupMessageRequestPacket.setGroupNickname(groupNickname);
            groupMessageRequestPacket.setMessage(message);
            channel.writeAndFlush(groupMessageRequestPacket);
        }
    }
}
