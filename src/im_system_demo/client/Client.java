package im_system_demo.client;

import im_system_demo.client.console_command.ConsoleCommandManager;
import im_system_demo.client.console_command.LoginConsoleCommand;
import im_system_demo.client.handler.*;
import im_system_demo.client.util.UserUtil;
import im_system_demo.proto.codec.PacketDecoder;
import im_system_demo.proto.codec.PacketEncoder;
import im_system_demo.proto.codec.Split;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;


/**
 * @author xiong
 * @date 2019-05-28  21:07
 */
public class Client {

    private String ip;
    private int port;
    private EventLoopGroup workGroup;
    private Bootstrap bootstrap;

    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
        this.workGroup = new NioEventLoopGroup();
        this.bootstrap = new Bootstrap();
    }

    public void service(){
        bootstrap.group(workGroup)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println(new Date() + " 客户端开始启动...");
                        ch.pipeline()
                                .addLast(new Split())
                                .addLast(new PacketDecoder())
                                .addLast(new LoginHandler())
                                .addLast(new MessageHandler())
                                .addLast(new CreateGroupHandler())
                                .addLast(new JoinGroupHandler())
                                .addLast(new QuitGroupHandler())
                                .addLast(new ShowGroupMembersHandler())
                                .addLast(new GroupMessageHandler())
                                .addLast(new LogoutHandler())
                                .addLast(new PacketEncoder());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            if(future.isSuccess()){
                Channel channel = future.channel();
                startConsoleThread(channel);
            }
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Client client = new Client("127.0.0.1",33333);
        client.service();
    }

    private static void startConsoleThread(Channel channel){
        ConsoleCommandManager consoleCommand = new ConsoleCommandManager();
        LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
        Scanner scanner = new Scanner(System.in);

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!UserUtil.hasLogin(channel)) {
                    loginConsoleCommand.exec(scanner, channel);
                } else {
                    consoleCommand.exec(scanner, channel);
                }
            }
        }).start();
    }

}
