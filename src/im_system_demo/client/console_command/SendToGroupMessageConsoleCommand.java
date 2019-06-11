package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
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
        System.out.print("发送的群名称: ");
        String nickname = scanner.nextLine();
        while (!Thread.interrupted() && nickname != null){
            System.out.print("TimeUtil.getTime() +  -> message to [" + nickname + "] :");
            String message = scanner.nextLine();
            if(!message.equals("quit group")){
                GroupMessageRequestPacket packet = new GroupMessageRequestPacket();
                packet.setGroupNickname(nickname);
                packet.setMessage(message);
                channel.writeAndFlush(packet);
            }else
                break;
        }
    }
}
