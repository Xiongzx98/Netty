package other.nio_socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;

/**
 * @author xiong
 * @date 2019-05-26  10:09
 */
public class IOServer {

    private Selector severSelector = null;
    private InetSocketAddress address = null;
    private Charset charset = Charset.forName("UTF-8");

    public IOServer(String ip, int port) throws IOException {
        this.severSelector = Selector.open();
        this.address = new InetSocketAddress(ip, port);
    }

    public static void main(String[] args) throws IOException {
        IOServer servers = new IOServer("127.0.0.1",8000);
        servers.service();
    }

    public void service() {
        try {
            ServerSocketChannel server = ServerSocketChannel.open();
            server.socket().bind(address);
            server.configureBlocking(false);
            server.register(severSelector, SelectionKey.OP_ACCEPT);

            while (severSelector.select() > 0) {
                for (SelectionKey sk : severSelector.selectedKeys()) {
                    severSelector.selectedKeys().remove(sk);

                    if (sk.isAcceptable()){
                        SocketChannel client = server.accept();
                        client.configureBlocking(false);
                        client.register(severSelector, SelectionKey.OP_READ);
                        sk.interestOps(SelectionKey.OP_ACCEPT);
                    }

                    if(sk.isReadable()){
                        SocketChannel client = (SocketChannel)sk.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        String content = "";

                        //如果捕捉到对应的channel异常，即表明该channel对应
                        //的client出现了问题，所以将取消其在selector的注册
                        try {
                            while (client.read(buffer) > 0) {
                                buffer.flip();
                                content += charset.decode(buffer);
                            }

                            System.out.println("读取到的数据： " + content);
                            sk.interestOps(SelectionKey.OP_READ);
                        }catch (IOException e) {
                            sk.channel();
                            if (sk.channel() != null)
                                sk.channel().close();
                        }

                        //content大于零，即聊天消息不为空
                        //向对应的channel发送消息
                        if(content.length() > 0){
                            for (SelectionKey key : severSelector.keys()){
                                //获取key对应的channel
                                Channel targetChannel = key.channel();

                                //如果channel是socketchannel对象
                                if(targetChannel instanceof SocketChannel){
                                    SocketChannel dest = (SocketChannel)targetChannel;
                                    dest.write(charset.encode(content));
                                }
                            }
                        }
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}























