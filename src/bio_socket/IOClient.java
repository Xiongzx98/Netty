package bio_socket;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @Author: Xiong
 * @Date: 2019/5/25 10:55 PM
 */
public class IOClient {

    Socket socket = null;

    public IOClient(String IPAddress, int port) throws IOException {
        socket = new Socket(IPAddress, port);
    }

    public static void main(String[] args) throws IOException {
        IOClient client = new IOClient("127.0.0.1", 9999);
        client.send();
    }

    public void send(){
        while (true){
            try {
                socket.getOutputStream().write((new Date() + ": hello world\n").getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
