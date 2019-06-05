package other.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author xiong
 * @date 2019-05-27  15:53
 */
public class NettyClient {

    private String ip;
    private int port;
    private Bootstrap bootstrap;
    private EventLoopGroup worker;

    private static final int MAX_RETRY = 5;

    public NettyClient(String ip, int port){
        this.ip = ip;
        this.port = port;
        this.bootstrap = new Bootstrap();
        this.worker = new NioEventLoopGroup();

    }

    public static void main(String[] args) {
        NettyClient client = new NettyClient("127.0.0.1", 9999);
        client.service();
    }

    public void service(){
        bootstrap.group(worker)
                .attr(AttributeKey.newInstance("clientName"), "nettyClient")
                .channel(NioSocketChannel.class)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
                .option(ChannelOption.SO_KEEPALIVE, true)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        System.out.println(ch.attr(AttributeKey.valueOf("clientName")).get());
                        ch.pipeline().addLast(new ClientHandler());
                    }
                });
        try {
            ChannelFuture future = bootstrap.connect(ip, port).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void connect(Bootstrap bootstrap, String host, int port, int retry) {
        bootstrap.connect(host, port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("连接成功!");
            } else if (retry == 0) {
                System.err.println("重试次数已用完，放弃连接！");
            } else {
                // 第几次重连
                int order = (MAX_RETRY - retry) + 1;
                // 本次重连的间隔
                int delay = 1 << order;
                System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
                bootstrap.group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
                        .SECONDS);
            }
        });
    }

}
