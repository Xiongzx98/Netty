package netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author xiong
 * @date 2019-05-27  14:36
 */
public class NettyServer {


//    private NioEventLoopGroup boss;
//    private NioEventLoopGroup worker;
    /**
     * 此处Group的选择基于是否跨平台，若不跨平台可以选择基于linux的epoll模型对象
     * EpollEventLoopGroup
     * NioEventLoopGroup则是
     */
    private EpollEventLoopGroup boss;
    private EpollEventLoopGroup worker;
    private ServerBootstrap bootstrap;
    private int port;

    public NettyServer(int port) {
        this.port = port;
        this.boss = new EpollEventLoopGroup();
        this.worker = new EpollEventLoopGroup();
        this.bootstrap = new ServerBootstrap();

    }

    public static void main(String[] args) {
        NettyServer servers = new NettyServer(9999);
        servers.service();
    }

    public void service() {
        try {
            bootstrap.group(boss, worker)
//                .channel(NioServerSocketChannel.class)
                    .channel(EpollServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .attr(AttributeKey.newInstance("serverName"), "nettyServer")
                    .childAttr(AttributeKey.newInstance("clientName"), "clientValue")
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .handler(new ChannelInitializer<EpollServerSocketChannel>() {
                        @Override
                        protected void initChannel(EpollServerSocketChannel ch) throws Exception {
                            System.out.println(ch.attr(AttributeKey.valueOf("serverName")).get());
                        }
                    })
                    .childHandler(new ChannelInitializer<EpollSocketChannel>() {
                        @Override
                        protected void initChannel(EpollSocketChannel ch) throws Exception {
                            System.out.println(ch.attr(AttributeKey.valueOf("clientName")).get());
                            ch.pipeline().addLast(new ServerHandler());
                        }
                    });

//        this.bind(bootstrap,this.ip, this.port);
            ChannelFuture f = bootstrap.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            worker.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    private void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap,port + 1);
                }
            }
        });
    }

}
