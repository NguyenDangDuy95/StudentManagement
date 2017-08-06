package helpers;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import threads.ServerThread;

public class Server {
    public void serve() {
        try {
            ServerSocket serverSocket = new ServerSocket(3001);

            System.out.println("Server is ready...");
            int count = 0;
            while (true) {
                System.out.println("Client" + ++count);
                Socket socket = serverSocket.accept();

                ServerThread st = new ServerThread(socket, count);
                st.start();
                if (st.getState() == Thread.State.TERMINATED) serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
