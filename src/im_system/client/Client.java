package im_system.client;

import im_system.client.handler.LoginRequestHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;


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
        bootstrap.channel(NioSocketChannel.class)
                .option(ChannelOption.SO_TIMEOUT, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new LoginRequestHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
