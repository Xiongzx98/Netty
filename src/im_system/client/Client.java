package im_system.client;

import im_system.proto.codec.PacketDecoder;
import im_system.proto.codec.PacketEncoder;
import im_system.proto.codec.Split;
import im_system.proto.request_packet.LoginRequestPacket;
import im_system.proto.request_packet.MessageRequestPacket;
import im_system.client.handler.LoginHandler;
import im_system.client.handler.MessageHandler;
import im_system.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;
import java.util.Scanner;


/**
 * @author xiong
 * @date 2019-05-28  18:07
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
        Scanner sc = new Scanner(System.in);
        LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (!SessionUtil.hasLogin(channel)) {
                    System.out.print("输入用户名登录: ");
                    String username = sc.nextLine();
                    loginRequestPacket.setUsername(username);

                    // 密码使用默认的
                    System.out.print("输入密码: ");
                    String pwd = sc.nextLine();
                    loginRequestPacket.setPassword(pwd);

                    // 发送登录数据包
                    channel.writeAndFlush(loginRequestPacket);
                    waitForLoginResponse();
                } else {
                    String toUsername = sc.nextLine();
                    String message = sc.nextLine();
                    channel.writeAndFlush(new MessageRequestPacket(toUsername, message));
                }
            }
        }).start();
    }

    private static void waitForLoginResponse() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {
        }
    }

}
