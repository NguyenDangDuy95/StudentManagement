package helpers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import threads.ServiceThread;

public class Server {

    private ServerSocket listener = null;
    private int clientNumber = 0;

    public void serve() throws IOException {
        System.out.println("Server is waiting to accept user...");
        try {
            listener = new ServerSocket(3002);
            while (true) {
                Socket socketOfServer = listener.accept();
                new ServiceThread(socketOfServer, clientNumber++).start();
            }

        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        } finally {
            listener.close();
        }
    }

}
