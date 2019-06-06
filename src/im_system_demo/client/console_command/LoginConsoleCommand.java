package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
import im_system_demo.proto.request_packet.LoginRequestPacket;
import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xiong
 * @date 2019-06-06  21:00
 */
public class LoginConsoleCommand implements ConsoleCommand {

    @Override
    public void exec(Scanner scanner, Channel channel) {
        LoginRequestPacket loginPacket = new LoginRequestPacket();

        System.out.println("请输入用户名：");
        loginPacket.setUsername(scanner.nextLine());
        System.out.println("请输入密码:");
        loginPacket.setPassword(scanner.nextLine());

        channel.writeAndFlush(loginPacket);
        waitForLoginResponse();

    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }
}
