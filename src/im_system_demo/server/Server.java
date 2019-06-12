package im_system_demo.server;

import im_system_demo.proto.codec.PacketCodecHandler;
import im_system_demo.proto.codec.Split;
import im_system_demo.server.handler.*;
import im_system_demo.server.redis.RedisPoll;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Date;

/**
 * @author xiong
 * @date 2019-05-28  21:07
 */
public class Server {

    private int port;
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ServerBootstrap serverBootstrap;

    public Server(int port){
        this.port = port;
        this.bossGroup = new NioEventLoopGroup();
        this.workerGroup = new NioEventLoopGroup();
        this.serverBootstrap = new ServerBootstrap();
    }

    public void service(){
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println(new Date() + ": 开始登陆...");
                        ch.pipeline()
                                .addLast(new IMIdleStateHandler())
                                .addLast(new Split())
                                .addLast(PacketCodecHandler.INSTANCE)
                                .addLast(LoginHandler.INSTANCE)
                                .addLast(HeartBeatHandler.INSTANCE)
                                .addLast(AuthHandler.INSTANCE)
                                .addLast(IMHandler.INSTANCE);
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
            RedisPoll.destroy();
        }
    }

    public static void main(String[] args) {
        Server server = new Server(33333);
        server.service();
    }

}
