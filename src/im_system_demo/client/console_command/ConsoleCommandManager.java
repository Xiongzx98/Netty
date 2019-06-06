package im_system_demo.client.console_command;

import im_system_demo.client.console_command.impl.ConsoleCommand;
import io.netty.channel.Channel;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author xiong
 * @date 2019-06-06  20:07
 */
public class ConsoleCommandManager implements ConsoleCommand {

    private Map<String, ConsoleCommand> consoleCommandMap;

    public ConsoleCommandManager(){
        this.consoleCommandMap = new HashMap<>();
        consoleCommandMap.put("sendToUser", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("creategroup", new CreateGroupConsoleCommand());
    }



    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.next();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if(consoleCommand != null){
            consoleCommand.exec(scanner, channel);
        }else {
            System.err.println("无法识别 "+command+" 指令，请重新输入!");
        }
    }
}
