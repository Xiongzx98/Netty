package im_system.servver;

import im_system.servver.handler.LoginResponseHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;

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
                .childOption(ChannelOption.TCP_NODELAY, true)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<EpollSocketChannel>() {
                    @Override
                    protected void initChannel(EpollSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoginResponseHandler());
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
}