package im_system_demo.client.console_command.impl;


import io.netty.channel.Channel;

import java.util.Scanner;

/**
 * @author xiong
 * @date 2019-06-06  20:07
 */
public interface ConsoleCommand {

    void exec(Scanner scanner, Channel channel);

}
