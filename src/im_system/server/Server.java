package im_system.server;

import im_system.proto.codec.PacketDecoder;
import im_system.proto.codec.PacketEncoder;
import im_system.server.handler.LoginHandler;
import im_system.server.handler.MessageHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;

import java.util.Date;

/**
 * @author xiong
 * @date 2019-05-28  18:07
 */
public class Server {

    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;

    public Server(int port){
        this.port = port;
        this.bossGroup = new EpollEventLoopGroup();
        this.workerGroup = new EpollEventLoopGroup();
        this.serverBootstrap = new ServerBootstrap();
    }

    public void service(){
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(EpollServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<EpollSocketChannel>() {
                    @Override
                    protected void initChannel(EpollSocketChannel ch) throws Exception {
                        System.out.println(new Date() + " 连接成功...");
                        ch.pipeline().addLast(new PacketDecoder())
                                .addLast(new LoginHandler())
                                .addLast(new MessageHandler())
                                .addLast(new PacketEncoder());
                    }
                });
        try {
            ChannelFuture future = serverBootstrap.bind(port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(33333);
        server.service();
    }

}
