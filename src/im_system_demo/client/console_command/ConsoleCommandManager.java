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
        consoleCommandMap.put("send to user", new SendToUserConsoleCommand());
        consoleCommandMap.put("logout", new LogoutConsoleCommand());
        consoleCommandMap.put("create group", new CreateGroupConsoleCommand());
        consoleCommandMap.put("join group", new JoinGroupConsoleCommand());
        consoleCommandMap.put("quit group", new QuitGroupConsoleCommand());
        consoleCommandMap.put("show group members", new ShowGroupMembersConsoleCommand());
    }



    @Override
    public void exec(Scanner scanner, Channel channel) {
        String command = scanner.nextLine();

        ConsoleCommand consoleCommand = consoleCommandMap.get(command);

        if(consoleCommand != null){
            consoleCommand.exec(scanner, channel);
        }else {
            System.err.println("无法识别 "+command+" 指令，请重新输入!");
        }
    }
}
