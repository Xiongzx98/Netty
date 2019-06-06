package other.bio_socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: Xiong
 * @Date: 2019/5/25 22:55 PM
 */
public class IOServer {

    ServerSocket serverSocket;

    /**
     *
     * @param port          绑定的端口号
     * @param IPAddress     绑定的IP地址
     * @param backlog       请求队列的大小，当serverSockert.accept()不被调用时，请求队列
     *                      只能容纳backlog个请求，默认是50个
     */
    public IOServer(int port, String IPAddress, int backlog) throws IOException {
        this.serverSocket = new ServerSocket(port);
    }

    public static void main(String[] args) throws IOException {

        IOServer server = new IOServer(9999,"127.0.0.1", 3);
        server.service();

    }

    public void service() {
        while (true){
            //serverSocket.accept() 从请求队列获得请求个数超出backlog时会触发IOException
            try {
                Socket socket = serverSocket.accept();
                new Thread(() -> {
                    try {
                        int len;
                        byte[] data = new byte[1024];
                        InputStream inputStream = socket.getInputStream();
                        //按字节流方式读取数据
                        while ((len = inputStream.read(data)) != -1) {
                            System.out.println(new String(data, 0, len));
                        }
                    } catch (IOException e) {
                    }
                }).start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
