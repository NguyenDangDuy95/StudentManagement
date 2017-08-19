package helpers;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class ServerConnection {
    public static ObjectInputStream ois = null;
    public static ObjectOutputStream oos = null;
    public static Socket socket;

    public static void connect() {
        try {
            System.out.println("Client 1 is ready");
            socket = new Socket(MyConstants.SERVERIP, MyConstants.PORT);  
            oos = new ObjectOutputStream(socket.getOutputStream());
            oos.flush();
            ois = new ObjectInputStream(socket.getInputStream());
        }catch(UnknownHostException e){
            System.out.println("Cannot connect to server!");
            return;
        }catch(IOException e){
            System.out.println("Cannot create I/O to server!");
            return;
        }
    }
}
